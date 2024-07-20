package com.stackyu.bbs.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 附件配置信息
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "bbs.attachment.upload")
public class AttachmentConfig {

    private String path;
}
