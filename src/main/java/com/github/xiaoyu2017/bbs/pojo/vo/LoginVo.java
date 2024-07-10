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

    private String code;
    private String userName;
    private String password;
}
