package com.stackyu.bbs.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stackyu.bbs.mapper.MenuMapper;
import com.stackyu.bbs.mapper.MenuMetaMapper;
import com.stackyu.bbs.mapper.MenuVueMapper;
import com.stackyu.bbs.pojo.bo.MenuBo;
import com.stackyu.bbs.pojo.dao.Menu;
import com.stackyu.bbs.pojo.dao.MenuMeta;
import com.stackyu.bbs.pojo.dao.MenuVue;
import com.stackyu.bbs.pojo.vo.MenuVo;
import com.stackyu.bbs.server.MenuService;
import com.stackyu.bbs.tool.BeanTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuMetaMapper menuMetaMapper;
    private final MenuVueMapper menuVueMapper;

    public MenuServiceImpl(MenuMapper menuMapper, MenuMetaMapper menuMetaMapper, MenuVueMapper menuVueMapper) {
        this.menuMapper = menuMapper;
        this.menuMetaMapper = menuMetaMapper;
        this.menuVueMapper = menuVueMapper;
    }

    @Transactional
    @Override
    public void saveMenu(MenuVo menuVo) {
        //vo to dto
        Menu menu = BeanTool.toTargetBean(menuVo, Menu.class);
        int insert = menuMapper.insert(menu);
        if (insert < 1) {
            log.error("insert menu error");
            throw new RuntimeException("insert menu error");
        }

        MenuMeta menuMeta = BeanTool.toTargetBean(menuVo.getMeta(), MenuMeta.class);
        menuMeta.setMenuId(menu.getId());
        int insertMeta = menuMetaMapper.insert(menuMeta);
        if (insertMeta < 1) {
            log.error("insert menu meta error");
            throw new RuntimeException("insert menu meta error");
        }

        MenuVue menuVue = BeanTool.toTargetBean(menuVo, MenuVue.class);
        menuVue.setMenuId(menu.getId());
        int insertVue = menuVueMapper.insert(menuVue);
        if (insertVue < 1) {
            log.error("insert menu vue error");
            throw new RuntimeException("insert menu vue error");
        }
    }

    @Override
    @Transactional
    public void deleteMenu(int id) {
        // 删除menu
        menuMapper.deleteById(id);

        // 删除对应vue信息
        QueryWrapper<MenuVue> menuVueWrapper = new QueryWrapper<>();
        menuVueWrapper.eq("menu_id", id);
        menuVueMapper.delete(menuVueWrapper);

        // 删除菜单meta信息
        QueryWrapper<MenuMeta> menuMateWrapper = new QueryWrapper<>();
        menuMateWrapper.eq("menu_id", id);
        menuMetaMapper.delete(menuMateWrapper);

        // 删除菜单对应子类
        QueryWrapper<Menu> menuWrapper = new QueryWrapper<>();
        menuWrapper.eq("parent_id", id);
        menuMapper.delete(menuWrapper);
    }

    @Override
    @Transactional
    public void updateMenu(MenuVo menuVo) {
        Menu menu = BeanTool.toTargetBean(menuVo, Menu.class);
        menuMapper.updateById(menu);

        MenuVue menuVue = BeanTool.toTargetBean(menuVo, MenuVue.class);
        QueryWrapper<MenuVue> menuVueWrapper = new QueryWrapper<>();
        menuVueWrapper.eq("menu_id", menu.getId());
        menuVueMapper.update(menuVue, menuVueWrapper);

        if (ObjectUtils.isNotEmpty(menuVo.getMeta())) {
            MenuMeta menuMeta = BeanTool.toTargetBean(menuVo.getMeta(), MenuMeta.class);
            QueryWrapper<MenuMeta> menuMetaWrapper = new QueryWrapper<>();
            menuMetaWrapper.eq("menu_id", menu.getId());
            menuMetaMapper.update(menuMeta, menuMetaWrapper);
        }
    }

    @Override
    public List<MenuVo> findMenuBySystemInfoId(int systemInfoId) {
        List<MenuBo> menuBos = menuMapper.selectMenuBySystemInfoId(systemInfoId);
        return BeanTool.menuBo2MenuVo(systemInfoId, menuBos);
    }

    @Override
    public List<MenuVo> findMenuByParentId(int menuId) {
        List<MenuBo> menuBos = menuMapper.selectMenuById(menuId);
        return BeanTool.menuBo2MenuVo(null, menuBos);
    }
}
