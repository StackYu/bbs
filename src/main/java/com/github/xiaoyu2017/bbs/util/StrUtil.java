package com.github.xiaoyu2017.bbs.util;

/**
 * 字符串相关处理工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class StrUtil {

    /** 用户名正则规则 */
    private static final String USER_NAME_RULE = "[\\d\\w]{1,10}";
    /** 密码正则规则 */
    private static final String USER_PASSWORD_RULE = "^(?![0-9A-Za-z]+$)(?![0-9A-Z\\W]+$)(?![0-9a-z\\W]+$)(?![A-Za-z\\W]+$)[0-9A-Za-z~!@#$%^&*()_+`\\-={}|\\[\\]\\\\:\";'<>?,./]{8,}$";

    // Redis
    public static final String AUTH_BLACK_LIST_KEY = "auth:black_list:";

    // Web
    public static final String USER_TOKEN = "USER_TOKEN";

    /**
     * 验证用户名称
     *
     * @param userName 用户名称
     * @return 是否正确
     */
    public static boolean userNameVerify(String userName) {
        return userName.matches(USER_NAME_RULE);
    }

    /**
     * 验证用户密码
     *
     * @param password 用户密码
     * @return 是否正确
     */
    public static boolean passwordVerify(String password) {
        return password.matches(USER_PASSWORD_RULE);
    }
}
