package com.github.xiaoyu2017.bbs.server.impl;

import com.github.xiaoyu2017.bbs.config.JwtConfig;
import com.github.xiaoyu2017.bbs.mapper.UserMapper;
import com.github.xiaoyu2017.bbs.pojo.bean.BbsContext;
import com.github.xiaoyu2017.bbs.pojo.bean.Payload;
import com.github.xiaoyu2017.bbs.pojo.bo.UserBo;
import com.github.xiaoyu2017.bbs.pojo.dao.UserDao;
import com.github.xiaoyu2017.bbs.pojo.dto.AuthDto;
import com.github.xiaoyu2017.bbs.pojo.dto.RegisterDto;
import com.github.xiaoyu2017.bbs.server.AuthService;
import com.github.xiaoyu2017.bbs.tool.BeanTool;
import com.github.xiaoyu2017.bbs.util.JwtUtil;
import com.github.xiaoyu2017.bbs.util.SnowFlakeUtil;
import com.github.xiaoyu2017.bbs.util.StrUtil;
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
    public String verify(AuthDto authDto) throws RuntimeException{
        UserBo userBo = BeanTool.toTargetBean(authDto, UserBo.class);
        // 设置密码MD5值
        userBo.setPassword(DigestUtils.md5DigestAsHex(userBo.getPassword().getBytes(StandardCharsets.UTF_8)));
        UserDao resultUser = userMapper.getUserByUserNameAndPassword(userBo);
        if (resultUser != null) {
            // 返回JWT
            String token = JwtUtil.generateTokenExpireInMinutes(resultUser, jwtConfig.getPrivateKey(), jwtConfig.getUser().getExpire());
            // 保存信息
            BbsContext bbsContext = new BbsContext(token);
            Payload<UserDao> infoFromToken = JwtUtil.getInfoFromToken(token, jwtConfig.getPublicKey(), UserDao.class);
            bbsContext.setUserDao(infoFromToken.getUserInfo());
            BbsContext.set(bbsContext);
            return token;
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
