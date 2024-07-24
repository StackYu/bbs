package com.stackyu.bbs.controller;

import com.stackyu.bbs.pojo.bean.Result;
import com.stackyu.bbs.pojo.bean.ResultCode;
import com.stackyu.bbs.pojo.vo.MenuVo;
import com.stackyu.bbs.server.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统信息
 * <p>
 * SystemInfo
 *
 * @author xiaoyu
 * @version 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping
    public ResponseEntity<Result> addMenu(MenuVo menuVo) {
        menuService.saveMenu(menuVo);
        return ResponseEntity.ok(Result.success(ResultCode.MENU_SUCCESS_200_1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteMenu(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.ok(Result.error(ResultCode.MENU_ERROR_400_1));
            }
            menuService.deleteMenu(id);
        } catch (Exception e) {
            return ResponseEntity.ok(Result.error(ResultCode.MENU_ERROR_400_2, e));
        }
        return ResponseEntity.ok(Result.success(ResultCode.MENU_SUCCESS_200_2));
    }

    @PutMapping
    public ResponseEntity<Result> updateMenu(MenuVo menuVo) {
        menuService.updateMenu(menuVo);
        return ResponseEntity.ok(Result.success(ResultCode.MENU_SUCCESS_200_3));
    }

    @GetMapping("/systemInfo/{systemInfoId}")
    public ResponseEntity<Result> getMenuBySystemInfoId(@PathVariable int systemInfoId) {
        List<MenuVo> menuVos = menuService.findMenuBySystemInfoId(systemInfoId);
        return ResponseEntity.ok(Result.success(ResultCode.MENU_SUCCESS_200, menuVos));
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Result> getMenuChildren(@PathVariable int menuId) {
        List<MenuVo> menuVos = menuService.findMenuByParentId(menuId);
        return ResponseEntity.ok(Result.success(ResultCode.MENU_SUCCESS_200, menuVos));
    }

}
