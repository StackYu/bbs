package com.stackyu.bbs.pojo.dto;

/**
 * 菜单元信息-DTO
 * <p>
 * MenuMetaDto
 *
 * @author xiaoyu
 * @version 1.0
 */
public class MenuMetaDto {
    /** 标题（唯一） */
    private String title;
    /** 图标 */
    private String icon;
    /** 是否缓存 */
    private Boolean noCache;
    /** 是否选择 */
    private Boolean activeMenu;

    private Integer menuId;
}
