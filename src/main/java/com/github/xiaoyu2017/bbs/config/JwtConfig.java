package com.github.xiaoyu2017.bbs.config;

import com.github.xiaoyu2017.bbs.util.RsaUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "bbs.jwt")
public class JwtConfig implements InitializingBean {

    private String pubKeyPath;
    private String priKeyPath;
    private JwtProperties user = new JwtProperties();

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 获取公钥和私钥
            this.publicKey = RsaUtil.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtil.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class JwtProperties {
        private Integer expire;
        private String cookieName;
        private String cookieDomain;
        private String minRefreshInterval;
    }
}
