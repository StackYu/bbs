package com.stackyu.bbs.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * AuthDto
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class AuthDto implements Serializable {
    private static final long serialVersionUID = -5411656989021625738L;

    private String userName;
    private String password;
}
