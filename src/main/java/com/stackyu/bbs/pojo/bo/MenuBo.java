package com.stackyu.bbs.pojo.bo;

import com.stackyu.bbs.pojo.dao.MenuMeta;
import com.stackyu.bbs.pojo.dao.MenuVue;
import com.stackyu.bbs.pojo.base.BaseEntity;
import com.stackyu.bbs.pojo.dao.SystemInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单类-DAO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuBo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2089276444843678774L;

    /** 菜单名称 */
    private String menuName;
    /** 菜单url */
    private String menuUrl;

    /** 子菜单 */
    private List<MenuBo> children;
    /** vue菜单信息 */
    private MenuVue menuVue;
    /** 菜单原数据 */
    private MenuMeta menuMeta;

}
