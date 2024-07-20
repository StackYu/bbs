package com.stackyu.bbs.pojo.vo;

import lombok.Data;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class SystemInfoVo {

    /** ID */
    protected Integer id;
    /** 系统ID */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 对外连接 */
    private String url;
    /** 内部链接 */
    private String link;

    private Integer parentId;
}
