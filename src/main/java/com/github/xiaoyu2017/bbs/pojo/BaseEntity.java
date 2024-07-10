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
    protected Integer id;
    protected Date createTime;
    protected Date updateTime;
    protected Integer status;
    protected Integer order;
    protected Integer version;
    protected Boolean delete;
}
