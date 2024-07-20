package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户类-pojo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDao extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3843944063258139566L;

    public UserDao() {
    }

    public UserDao(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private String userName;
    private String password;
    private String uid;
}
