package com.stackyu.bbs.pojo.dto;

import lombok.Data;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class AttachmentDto {
    /** 附件ID */
    private String aid;
    /** 文件MIME */
    private String mime;
    /** 文件大小 */
    private Integer size;
    /** 文件大小单位 */
    private String sizeUnit;
}
