package com.github.xiaoyu2017.bbs.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录页面VO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = -1093255340622633718L;

    /** 用户名 */
    private String userName;
    /** 用户密码 */
    private String password;
    /** 是否记住密码 */
    private Boolean rememberPassword = false;
    /** 请求类别（0:正常请求，1:remember请求） */
    private Integer category;
    /** 验证码 */
    private String code;
    /** 记住密码token */
    private String rememberToken;
}
