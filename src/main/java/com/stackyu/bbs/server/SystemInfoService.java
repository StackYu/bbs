package com.stackyu.bbs.server;

import com.stackyu.bbs.pojo.vo.SystemInfoVo;

import java.util.List;

/**
 * 系统信息服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface SystemInfoService {

    List<SystemInfoVo> findAllRootSystemInfo();

    List<SystemInfoVo> findSystemInfoChildren(int parentId);

    void saveSystemInfo(SystemInfoVo systemInfoVo);

    void deleteSystemInfo(int id);

    void updateSystemInfo(SystemInfoVo systemInfoVo);
}
