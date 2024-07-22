package com.stackyu.bbs.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletResponse;

/**
 * Cookie操作工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String key, String vul) {
        ResponseCookie cookie = ResponseCookie.from(key, vul)
                // 禁止js读取
                .httpOnly(true)
                // 在http下也传输
                .secure(true)
                // path
                .path("/")
                // 新增加的部分 允许在跨站点请求中发送
                .sameSite("None")
                .domain("localhost")
                .build();
        // 设置Cookie Header
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        response.setHeader(StrUtil.USER_TOKEN, cookie.toString());

        log.info("设置cookie完成,{}: {}", key, cookie);
    }
}
