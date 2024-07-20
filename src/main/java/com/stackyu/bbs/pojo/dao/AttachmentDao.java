package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 附件类-pojo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttachmentDao extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 23897982786643123L;

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

    /** 父对象ID */
    private Integer parentId;
}
