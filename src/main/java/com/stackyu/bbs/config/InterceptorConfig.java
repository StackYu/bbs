package com.stackyu.bbs.config;

import com.stackyu.bbs.interceptor.AuthHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器相关配置
 *
 * @author xiaoyu
 * @version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final String[] authWhiteList = {"/login", "/register"};

    private final AuthHandlerInterceptor authHandlerInterceptor;

    public InterceptorConfig(AuthHandlerInterceptor authHandlerInterceptor) {
        this.authHandlerInterceptor = authHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(authWhiteList);*/
    }

}
