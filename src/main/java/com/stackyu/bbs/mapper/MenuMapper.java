package com.stackyu.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stackyu.bbs.pojo.bo.MenuBo;
import com.stackyu.bbs.pojo.dao.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单Menu数据库操作类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuBo> selectMenuBySystemInfoId(int systemInfo);

    List<MenuBo> selectMenuById(int id);
}
