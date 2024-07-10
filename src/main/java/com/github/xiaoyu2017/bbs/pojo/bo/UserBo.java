package com.github.xiaoyu2017.bbs.pojo.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class UserBo implements Serializable {
    private static final long serialVersionUID = 3088499361209271510L;

    private String userName;
    private String password;
    private String uid;
}
