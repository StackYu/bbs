package com.stackyu.bbs.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    protected Integer id;
    /** 创建时间 */
    protected Date createTime;
    /** 修改时间 */
    protected Date updateTime;
    /** 状态 */
    @TableField("`status`")
    protected Integer status;
    /** 顺序 */
    @TableField("`order`")
    protected Integer order;
    /** 版本 */
    @TableField("`version`")
    protected Integer version;
    /** 是否删除 */
    @TableField("`delete`")
    protected Boolean delete;
}
