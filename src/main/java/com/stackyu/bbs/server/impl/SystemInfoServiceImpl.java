package com.stackyu.bbs.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stackyu.bbs.mapper.SystemInfoMapper;
import com.stackyu.bbs.pojo.dao.SystemInfo;
import com.stackyu.bbs.pojo.vo.SystemInfoVo;
import com.stackyu.bbs.server.SystemInfoService;
import com.stackyu.bbs.tool.BeanTool;
import com.stackyu.bbs.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统信息服务实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Service
public class SystemInfoServiceImpl implements SystemInfoService {
    @Autowired
    SystemInfoMapper systemInfoMapper;

    @Override
    public List<SystemInfoVo> findAllRootSystemInfo() {
        QueryWrapper<SystemInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("`status`", 0);
        queryWrapper.eq("`delete`", 0);
        queryWrapper.isNull(("`parent_id`"));
        queryWrapper.orderByAsc("`create_time`");
        List<SystemInfo> systemInfos = systemInfoMapper.selectList(queryWrapper);
        return systemInfos.stream().map(i -> BeanTool.toTargetBean(i, SystemInfoVo.class)).collect(Collectors.toList());
    }

    @Override
    public List<SystemInfoVo> findSystemInfoChildren(int parentId) {
        QueryWrapper<SystemInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("`status`", 0);
        queryWrapper.eq("`delete`", 0);
        queryWrapper.eq("`parent_id`", parentId);
        queryWrapper.orderByAsc("`create_time`");
        List<SystemInfo> systemInfos = systemInfoMapper.selectList(queryWrapper);
        return systemInfos.stream().map(i -> BeanTool.toTargetBean(i, SystemInfoVo.class)).collect(Collectors.toList());
    }

    @Override
    public void saveSystemInfo(SystemInfoVo systemInfoVo) {
        SystemInfo systemInfo = BeanTool.toTargetBean(systemInfoVo, SystemInfo.class);
        systemInfo.setSystemId(StrUtil.uuidStr());
        int insert = systemInfoMapper.insert(systemInfo);
    }

    @Override
    @Transactional
    public void deleteSystemInfo(int id) {
        systemInfoMapper.deleteById(id);

        QueryWrapper<SystemInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        systemInfoMapper.delete(wrapper);
    }

    @Override
    public void updateSystemInfo(SystemInfoVo systemInfoVo) {
        SystemInfo targetBean = BeanTool.toTargetBean(systemInfoVo, SystemInfo.class);
        systemInfoMapper.updateById(targetBean);
    }
}
