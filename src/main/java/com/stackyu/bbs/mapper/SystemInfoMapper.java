package com.stackyu.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stackyu.bbs.pojo.dao.SystemInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统信息数据库操作类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface SystemInfoMapper extends BaseMapper<SystemInfo> {
}
