package com.github.xiaoyu2017.bbs.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-vo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@ApiModel("用户VO")
public class UserVo implements Serializable {
    private static final long serialVersionUID = 4946794967280096643L;

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String userName;
    @ApiModelProperty(value = "密码", required = true, example = "admin")
    private String password;
}
