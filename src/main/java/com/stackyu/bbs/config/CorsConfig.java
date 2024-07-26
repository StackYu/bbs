package com.stackyu.bbs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 对所有接口都应用CORS配置
        registry.addMapping("/**")
                // 允许任何域名,不要写*，否则cookie就无法使用了
                .allowedOrigins("http://localhost:8000")
                .allowedOrigins("http://192.168.0.124:8000")
                .allowedOrigins("http://192.168.0.124")
                // 允许的请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
                // 允许的请求头
                .allowedHeaders("*")
                // 是否允许证书（cookies）
                .allowCredentials(true);
    }
}
