package com.github.xiaoyu2017.bbs.server;

import com.github.xiaoyu2017.bbs.pojo.dto.UserDto;

/**
 * User用户服务类-接口
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface UserService {
    UserDto save(UserDto userDto);
}
