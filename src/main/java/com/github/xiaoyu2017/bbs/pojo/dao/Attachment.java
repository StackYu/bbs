package com.github.xiaoyu2017.bbs.pojo.dao;

import com.github.xiaoyu2017.bbs.pojo.BaseEntity;
import com.github.xiaoyu2017.bbs.pojo.bean.AttachmentCode;
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
public class Attachment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 23897982786643123L;

    /** 附件路径 */
    private String path;
    /** 附件ID */
    private Integer aid;
    /** 附件类型 */
    private AttachmentCode code;
}
