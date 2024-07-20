package com.stackyu.bbs.server.impl;

import com.stackyu.bbs.config.JwtConfig;
import com.stackyu.bbs.mapper.UserMapper;
import com.stackyu.bbs.pojo.bean.BbsContext;
import com.stackyu.bbs.pojo.bean.Payload;
import com.stackyu.bbs.pojo.bo.UserBo;
import com.stackyu.bbs.pojo.dao.UserDao;
import com.stackyu.bbs.pojo.dto.RegisterDto;
import com.stackyu.bbs.pojo.vo.LoginVo;
import com.stackyu.bbs.pojo.vo.UserVo;
import com.stackyu.bbs.server.AuthService;
import com.stackyu.bbs.tool.BeanTool;
import com.stackyu.bbs.util.JwtUtil;
import com.stackyu.bbs.util.SnowFlakeUtil;
import com.stackyu.bbs.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 用户相关服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final JwtConfig jwtConfig;
    private final StringRedisTemplate stringRedisTemplate;

    public AuthServiceImpl(UserMapper userMapper, JwtConfig jwtConfig, StringRedisTemplate stringRedisTemplate) {
        this.userMapper = userMapper;
        this.jwtConfig = jwtConfig;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        UserBo userBo = BeanTool.toTargetBean(registerDto, UserBo.class);
        // 设置标识ID
        userBo.setUid(SnowFlakeUtil.getStrSnowFlakeId());
        // 设置密码MD5值
        userBo.setPassword(DigestUtils.md5DigestAsHex(userBo.getPassword().getBytes(StandardCharsets.UTF_8)));
        int i = userMapper.insertUserDao(userBo);
        return i == 1;
    }

    @Override
    public UserVo verify(LoginVo loginVo) throws RuntimeException {
        UserBo userBo = BeanTool.toTargetBean(loginVo, UserBo.class);
        if (loginVo.getCategory() == 0) {
            // 设置密码MD5值
            userBo.setPassword(DigestUtils.md5DigestAsHex(userBo.getPassword().getBytes(StandardCharsets.UTF_8)));
        } else {
            Payload<String> infoFromToken = JwtUtil.getInfoFromToken(loginVo.getRememberToken(), jwtConfig.getPublicKey(), String.class);
            userBo.setPassword(infoFromToken.getUserInfo());
        }

        UserDao resultUser = userMapper.getUserByUserNameAndPassword(userBo);
        if (resultUser != null) {
            // 返回JWT
            String token = JwtUtil.generateTokenExpireInMinutes(resultUser, jwtConfig.getPrivateKey(), jwtConfig.getUser().getExpire());
            String rememberToken = null;
            // 是否记住密码
            if (loginVo.getRememberPassword()) {
                // 三天过期时间
                rememberToken = JwtUtil.generateTokenExpireInMinutes(resultUser.getPassword(), jwtConfig.getPrivateKey(), 60 * 24 * 3);
            }

            // 保存信息
            BbsContext bbsContext = new BbsContext(token);
            bbsContext.setUserDao(resultUser);
            BbsContext.set(bbsContext);

            // 返回信息
            UserVo userVo = new UserVo();
            userVo.setUserName(resultUser.getUserName());
            userVo.setPassword(resultUser.getPassword());
            userVo.setToken(token);
            userVo.setRememberToken(rememberToken);
            userVo.setUid(resultUser.getUid());
            return userVo;
        } else {
            return null;
        }
    }

    @Override
    public boolean verifyToken(String token) {
        Payload<UserDao> infoFromToken = null;
        try {
            infoFromToken = JwtUtil.getInfoFromToken(token, jwtConfig.getPublicKey(), UserDao.class);
        } catch (Exception e) {
            return false;
        }
        return infoFromToken.getExpiration().getTime() > System.currentTimeMillis();
    }

    @Override
    public String refreshToken(String token) {
        return null;
    }

    @Override
    public boolean logout(HttpServletResponse response) {
        try {
            String token = BbsContext.get().getToken();
            String uid = BbsContext.get().getUserDao().getUid();
            stringRedisTemplate.opsForValue().set(StrUtil.AUTH_BLACK_LIST_KEY + uid, token, 10, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isLapse(String token) {
        Payload<UserDao> infoFromToken = JwtUtil.getInfoFromToken(token, jwtConfig.getPublicKey(), UserDao.class);
        String string = stringRedisTemplate.opsForValue().get(StrUtil.AUTH_BLACK_LIST_KEY
                + infoFromToken.getUserInfo().getUid());
        return !StringUtils.isBlank(string);
    }

    @Override
    public boolean isRefresh(String token) {
        Payload<UserDao> infoFromToken = JwtUtil.getInfoFromToken(token, jwtConfig.getPublicKey(), UserDao.class);
        return !(infoFromToken.getExpiration().getTime() > System.currentTimeMillis());
    }
}
