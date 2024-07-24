package com.stackyu.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stackyu.bbs.pojo.dao.MenuMeta;
import com.stackyu.bbs.pojo.dao.MenuVue;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单元数据MenuMeta数据库操作类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface MenuMetaMapper extends BaseMapper<MenuMeta> {
}
