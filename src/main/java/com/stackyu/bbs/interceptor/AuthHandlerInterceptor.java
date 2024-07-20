package com.stackyu.bbs.interceptor;

import com.stackyu.bbs.pojo.bean.BbsContext;
import com.stackyu.bbs.pojo.bean.Payload;
import com.stackyu.bbs.pojo.bean.Result;
import com.stackyu.bbs.pojo.bean.ResultCode;
import com.stackyu.bbs.pojo.dao.UserDao;
import com.stackyu.bbs.server.AuthService;
import com.stackyu.bbs.config.JwtConfig;
import com.stackyu.bbs.util.JsonUtils;
import com.stackyu.bbs.util.JwtUtil;
import com.stackyu.bbs.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 用户验证相关拦截器
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    AuthService authService;
    @Autowired
    JwtConfig jwtConfig;

    /**
     * controller执行前进行拦截
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return 是否通过
     * @throws Exception 异常信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String token = null;
        try {
            token = Stream.of(request.getCookies())
                    .filter(i -> i.getName().equals(StrUtil.USER_TOKEN))
                    .findFirst()
                    .get()
                    .getValue();
        } catch (Exception e) {
            log.error("获得用户token失败！");
            log.error("失败原因：" + e.getMessage());
            return false;
        }
        // token为空
        if (StringUtils.isBlank(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(dontHavePermissions());
            return false;
        }
        // 校验token
        if (!authService.verifyToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(dontHavePermissions());
            return false;
        }
        // token是否过期
        if (authService.isLapse(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(dontHavePermissions());
            return false;
        }
        // 是否需要刷新
        if (authService.isRefresh(token)) {
            token = authService.refreshToken(token);
            Cookie cookie = new Cookie(StrUtil.USER_TOKEN, token);
            response.addCookie(cookie);
        }
        // 判断是否保存用户信息
        if (Objects.isNull(BbsContext.get()) || StringUtils.isBlank(BbsContext.get().getToken())) {
            BbsContext bbsContext = new BbsContext(token);
            Payload<UserDao> infoFromToken = JwtUtil.getInfoFromToken(token, jwtConfig.getPublicKey(), UserDao.class);
            bbsContext.setUserDao(infoFromToken.getUserInfo());
            BbsContext.set(bbsContext);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private String dontHavePermissions() {
        return JsonUtils.toString(Result.success(ResultCode.DONT_HAVE_PERMISSIONS_401));
    }

}
