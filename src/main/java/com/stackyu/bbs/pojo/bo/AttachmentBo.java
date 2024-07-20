package com.stackyu.bbs.pojo.bo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class AttachmentBo {

    /** 附件ID */
    private String aid;
    /** 附件路径 */
    private String path;
    /** 附件类型 */
    private Integer type;
    /** 文件原始名称 */
    private String originalFilename;
    /** 文件MIME */
    private String mime;
    /** 文件大小 */
    private Integer size;
    /** 文件大小单位 */
    private String sizeUnit;

    private MultipartFile multipartFile;
}
