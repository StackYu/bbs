package com.github.xiaoyu2017.bbs.pojo.dao;

import com.github.xiaoyu2017.bbs.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基础信息类-pojo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBase extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3235860858169087529L;

    /** 昵称 */
    private String nickName;
    /** 真实姓名 */
    private String realName;
    /** 性别 */
    private String sex;
    /** 生日 */
    private Date birthday;
    /** 出生地 */
    private String birthplace;
    /** 现居地 */
    private String nowPlace;
    /** 毕业学校 */
    private String school;
    /** 文化程度 */
    private String education;
    /** 所在公司 */
    private String company;
    /** 邮箱 */
    private String email;
    /** 手机号码 */
    private String phone;
    /** QQ */
    private String qq;
}
