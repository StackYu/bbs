package com.stackyu.bbs.tool;

import com.stackyu.bbs.pojo.bo.MenuBo;
import com.stackyu.bbs.pojo.vo.MenuMetaVo;
import com.stackyu.bbs.pojo.vo.MenuVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * bean相关工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class BeanTool {

    public static <T> T toTargetBean(Object source, Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toTargetBean(Class<T> clazz, Object... sources) {
        List<T> list;
        try {
            list = new ArrayList<>(sources.length);
            for (Object source : sources) {
                T target = clazz.newInstance();
                BeanUtils.copyProperties(source, target);
                list.add(target);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public static List<MenuVo> menuBo2MenuVo(Integer systemInfoId, List<MenuBo> menuBos) {
        if (ObjectUtils.isEmpty(menuBos) || menuBos.isEmpty()) {
            return null;
        }
        List<MenuVo> menuVoList = new ArrayList<>();
        for (MenuBo menuBo : menuBos) {
            MenuVo menuVo = new MenuVo();
            menuVo.setId(menuBo.getId());
            menuVo.setMenuName(menuBo.getMenuName());
            menuVo.setMenuUrl(menuBo.getMenuUrl());
            menuVo.setSystemInfoId(systemInfoId);

            if (ObjectUtils.isNotEmpty(menuBo.getMenuVue())) {
                menuVo.setName(menuBo.getMenuVue().getName());
                menuVo.setPath(menuBo.getMenuVue().getPath());
                menuVo.setComponentPath(menuBo.getMenuVue().getComponentPath());
                menuVo.setRedirect(menuBo.getMenuVue().getRedirect());
                menuVo.setAlwaysShow(menuBo.getMenuVue().getAlwaysShow());
                menuVo.setHidden(menuBo.getMenuVue().getHidden());
                menuVo.setHasChildren(menuBo.getMenuVue().getHasChildren());
            }

            if (ObjectUtils.isNotEmpty(menuBo.getMenuMeta())) {
                MenuMetaVo menuMetaVo = new MenuMetaVo();
                menuMetaVo.setTitle(menuBo.getMenuMeta().getTitle());
                menuMetaVo.setIcon(menuBo.getMenuMeta().getIcon());
                menuMetaVo.setNoCache(menuBo.getMenuMeta().getNoCache());
                menuMetaVo.setActiveMenu(menuBo.getMenuMeta().getActiveMenu());
                menuVo.setMeta(menuMetaVo);
            }

            if (ObjectUtils.isNotEmpty(menuBo.getChildren()) && !menuBo.getChildren().isEmpty()) {
                menuVo.setChildren(menuBo2MenuVo(null, menuBo.getChildren()));
            }

            menuVoList.add(menuVo);
        }
        return menuVoList;
    }
}
