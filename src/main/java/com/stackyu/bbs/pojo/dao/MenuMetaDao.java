package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 菜单原数据类-DAO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuMetaDao extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6708378896040746155L;

    /** 标题（唯一） */
    private String title;
    /** 图标 */
    private String icon;
    /** 是否缓存 */
    private Boolean noCache;
    /** 是否选择 */
    private Boolean activeMenu;

    // MenuDao id
    private Integer menuId;
}
