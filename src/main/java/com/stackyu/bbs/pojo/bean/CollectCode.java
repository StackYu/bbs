package com.stackyu.bbs.pojo.bean;

import lombok.Getter;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Getter
public enum CollectCode {
    USER(1, "用户"),
    ARTICLE(2, "文章"),
    PLATE(3, "板块");

    CollectCode(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private final Integer code;
    private final String value;
}
