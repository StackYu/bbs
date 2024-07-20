package com.stackyu.bbs.server;

import com.stackyu.bbs.pojo.dto.UserDto;

/**
 * User用户服务类-接口
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface UserService {
    UserDto save(UserDto userDto);
}
