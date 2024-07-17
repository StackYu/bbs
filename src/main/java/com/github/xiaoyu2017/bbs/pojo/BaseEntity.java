package com.github.xiaoyu2017.bbs.pojo;

import lombok.Data;

import java.util.Date;

/**
 * bean基础类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class BaseEntity {
    /** ID */
    protected Integer id;
    /** 创建时间 */
    protected Date createTime;
    /** 修改时间 */
    protected Date updateTime;
    /** 状态 */
    protected Integer status;
    /** 顺序 */
    protected Integer order;
    /** 版本 */
    protected Integer version;
    /** 是否删除 */
    protected Boolean delete;
}
