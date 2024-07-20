package com.stackyu.bbs.controller;

import com.stackyu.bbs.pojo.bean.Result;
import com.stackyu.bbs.pojo.bean.ResultCode;
import com.stackyu.bbs.pojo.vo.SystemInfoVo;
import com.stackyu.bbs.server.SystemInfoService;
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
@RequestMapping("/systemInfo")
public class SystemInfoController {

    @Autowired
    SystemInfoService systemInfoService;

    @PostMapping
    public ResponseEntity<Result> addSystemInfo(SystemInfoVo systemInfoVo) {
        systemInfoService.saveSystemInfo(systemInfoVo);
        return ResponseEntity.ok(Result.success(ResultCode.SYSTEM_INFO_SUCCESS_200_1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteSystemInfo(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.ok(Result.error(ResultCode.SYSTEM_INFO_ERROR_400_1));
            }
            systemInfoService.deleteSystemInfo(id);
        } catch (Exception e) {
            return ResponseEntity.ok(Result.error(ResultCode.SYSTEM_INFO_ERROR_400_2, e));
        }
        return ResponseEntity.ok(Result.success(ResultCode.SYSTEM_INFO_SUCCESS_200_2));
    }

    @PutMapping
    public ResponseEntity<Result> updateSystemInfo(SystemInfoVo systemInfoVo) {
        systemInfoService.updateSystemInfo(systemInfoVo);
        return ResponseEntity.ok(Result.success(ResultCode.SYSTEM_INFO_SUCCESS_200_3));
    }

    @GetMapping
    public ResponseEntity<Result> getAllRootSystemInfo() {
        List<SystemInfoVo> allSystemInfo = systemInfoService.findAllRootSystemInfo();
        return ResponseEntity.ok(Result.success(ResultCode.SYSTEM_INFO_SUCCESS_200, allSystemInfo));
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<Result> getSystemInfoChildren(@PathVariable int parentId) {
        List<SystemInfoVo> allSystemInfo = systemInfoService.findSystemInfoChildren(parentId);
        return ResponseEntity.ok(Result.success(ResultCode.SYSTEM_INFO_SUCCESS_200, allSystemInfo));
    }
}
