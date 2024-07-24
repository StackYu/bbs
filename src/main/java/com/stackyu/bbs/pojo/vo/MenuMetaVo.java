package com.stackyu.bbs.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 菜单元数据-VO
 * <p>
 * MenuMateVo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuMetaVo {
    /** 标题（唯一） */
    private String title;
    /** 图标 */
    private String icon;
    /** 是否缓存 */
    private Boolean noCache;
    /** 是否选择 */
    private Boolean activeMenu;
}
