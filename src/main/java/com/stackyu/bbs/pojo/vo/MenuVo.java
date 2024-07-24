package com.stackyu.bbs.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 菜单类-VO
 * <p>
 * MenuVo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {

    protected Integer id;
    /** 菜单名称（唯一） */
    private String menuName;
    /** 菜单url */
    private String menuUrl;
    /** MenuDao id */
    private Integer parentId;
    /** SystemInfo id */
    private Integer systemInfoId;
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

    private MenuMetaVo meta;

    private List<MenuVo> children;
}
