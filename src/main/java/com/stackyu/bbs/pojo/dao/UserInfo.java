package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户个人信息类-pojo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7484584218713688049L;

    /** 网站网址 */
    private String website;
    /** 个人简介 */
    private String about;
    /** 兴趣爱好 */
    private String hobby;
    /** 个人签名 */
    private String signature;
    /** 时区 */
    private String timezone;
}
