package com.stackyu.bbs.pojo.dto;

import lombok.Data;

/**
 * 菜单Vue-DTO
 * <p>
 * MenuVueDto
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class MenuVueDto {
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

    private Integer menuId;
}
