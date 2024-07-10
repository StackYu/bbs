package com.github.xiaoyu2017.bbs.server;

import com.github.xiaoyu2017.bbs.pojo.dto.AuthDto;
import com.github.xiaoyu2017.bbs.pojo.dto.RegisterDto;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户验证相关服务
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface AuthService {

    boolean register(RegisterDto registerDto);

    /**
     * 验证用户名和密码
     * <p>
     * 验证通过进行授权，返回JWT
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return 成功后的JWT字符串
     */
    String verify(AuthDto authDto);

    /**
     * 验证token正确性
     *
     * @param token 用户登录token
     * @return 是否正确头可能
     */
    boolean verifyToken(String token);

    /**
     * 刷新token过期时间
     *
     * @param token 用户token
     * @return 重新刷新的token
     */
    String refreshToken(String token);

    /**
     * 退出登录
     *
     * @param token 用户token
     * @return 是否成功
     */
    boolean logout(HttpServletResponse response);

    /**
     * 判断token是否失效
     *
     * @param token 用户token
     * @return 是否失效
     */
    boolean isLapse(String token);

    /**
     * 判断token是否需要刷新
     *
     * @param token 用户token
     * @return 是否需要刷新
     */
    boolean isRefresh(String token);
}
