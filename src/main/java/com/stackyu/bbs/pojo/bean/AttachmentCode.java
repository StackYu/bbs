package com.stackyu.bbs.pojo.bean;

import lombok.Getter;

import java.util.Arrays;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Getter
public enum AttachmentCode {
    USER_ICON(1, "用户头像"),
    ARTICLE_IMG(2, "文章图片");

    AttachmentCode(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static AttachmentCode valueOf(int type) {
        return Arrays.stream(AttachmentCode.values()).filter(i -> i.getCode() == type).findFirst().get();
    }

    private final Integer code;
    private final String value;

}
