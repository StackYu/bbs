package com.stackyu.bbs.pojo.bo;

import com.stackyu.bbs.pojo.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemInfoBo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6526089143059541175L;

    /** 系统ID */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 对外连接 */
    private String url;
    /** 内部链接 */
    private String link;

    /** SystemInfoBo parent */
    private SystemInfoBo parent;
    /** SystemInfoBo children */
    private List<SystemInfoBo> children;
}
