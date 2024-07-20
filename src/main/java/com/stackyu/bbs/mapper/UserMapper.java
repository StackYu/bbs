package com.stackyu.bbs.mapper;

import com.stackyu.bbs.pojo.dao.UserDao;
import com.stackyu.bbs.pojo.bo.UserBo;
import org.apache.ibatis.annotations.Mapper;

/**
 * User用户数据库操作类-mapper
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return int 影响行数
     */
    int insertUserDao(UserBo userBo);

    /**
     * 根据用户名和密码查询用户
     *
     * @param user 用户信息
     * @return 行数
     */
    UserDao getUserByUserNameAndPassword(UserBo userBo);

}
