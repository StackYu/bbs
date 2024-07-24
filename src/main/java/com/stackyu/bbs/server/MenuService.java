package com.stackyu.bbs.server;

import com.stackyu.bbs.pojo.vo.MenuVo;

import java.util.List;

/**
 * 菜单服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface MenuService {
    void saveMenu(MenuVo menuVo);

    void deleteMenu(int id);

    void updateMenu(MenuVo menuVo);

    List<MenuVo> findMenuBySystemInfoId(int systemInfoId);

    List<MenuVo> findMenuByParentId(int menuId);
}
