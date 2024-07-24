package com.stackyu.bbs.pojo.dto;
import lombok.Data;
/**
 * 菜单-DTO
 * <p>
 * MenuDto
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class MenuDto {
    protected Integer id;
    /** 菜单名称（唯一） */
    private String menuName;
    /** 菜单url */
    private String menuUrl;
    /** MenuDao id */
    private Integer parentId;
    /** SystemInfo id */
    private Integer systemInfoId;

    private MenuVueDto vue;
    private MenuMetaDto meta;
}
