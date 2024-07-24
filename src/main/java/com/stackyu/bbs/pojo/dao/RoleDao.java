package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.base.BaseEntity;

import java.io.Serializable;

/**
 * 角色类-DAO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class RoleDao extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5432137152884006287L;

    private String roleName;
    private String roleId;
}
