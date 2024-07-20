package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统菜单类-DAO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDao extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 4734581510896574763L;

    /** 菜单名称（唯一） */
    private String menuName;
    /** 菜单url */
    private String menuUrl;

    /** MenuDao id */
    private Integer parentId;
    /** SystemInfo id */
    private Integer systemInfoId;
}
