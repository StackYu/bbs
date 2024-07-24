package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统菜单针对VUE类-DAO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVue extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1093234829871477728L;
    /** 路由名称 */
    private String name;
    /** 路由路径 */
    private String path;
    /** 菜单组件路径 */
    private String componentPath;
    /** 跳转路径 */
    private String redirect;
    /** 总是显示 */
    private String alwaysShow;
    /** 是否隐藏 */
    private Boolean hidden;
    /** 是否有子菜单 */
    private Boolean hasChildren;


    /** Menu Id */
    private Integer menuId;

}
