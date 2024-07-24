package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统相关信息
 * <p>
 * 或系统子功能模块
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1143321426980002489L;

    /** 系统ID */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 对外连接 */
    private String url;
    /** 内部链接 */
    private String link;

    /** SystemInfoDao ID */
    private Integer parentId;
}
