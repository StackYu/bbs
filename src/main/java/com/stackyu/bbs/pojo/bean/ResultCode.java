package com.stackyu.bbs.pojo.bean;

import lombok.Getter;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Getter
public enum ResultCode {
    // 数据新增
    INSERT_SUCCESS_200("2000", "新增成功"),
    INSERT_ERROR_400("4000", "新增失败"),
    // 数据删除
    DELETE_SUCCESS_200("2000", "删除成功"),
    DELETE_ERROR_400("4000", "删除失败"),

    // 登录 1
    LOGIN_SUCCESS_200("100200", "登录成功"),
    LOGIN_ERROR_400_1("101400", "请输入登录内容"),
    LOGIN_ERROR_400_2("102400", "用户名或密码错误"),
    LOGIN_ERROR_500("100500", "服务器出错请稍等"),

    // 登出 2
    LOGOUT_SUCCESS_200("200200", "登出成功"),
    LOGOUT_ERROR_400("200400", "登出失败"),
    LOGOUT_ERROR_500("200500", "服务器出错请稍等"),

    // 没有权限 3
    DONT_HAVE_PERMISSIONS_401("301400", "无权访问"),
    DONT_HAVE_PERMISSIONS_402("302400", "需要登录"),

    // 用户注册 4
    REGISTER_SUCCESS_200("400200", "注册成功"),
    REGISTER_ERROR_400_1("401400", "请输入注册内容"),
    REGISTER_ERROR_400_2("402400", "请输正确用户名"),
    REGISTER_ERROR_400_3("403400", "请输正确用户密码"),
    REGISTER_ERROR_500("400500", "注册失败，请稍后重试"),

    // 附件上传
    UPLOAD_SUCCESS_200("500200", "上传文件成功"),
    UPLOAD_ERROR_400_1("501400", "上传文件不能为空"),
    UPLOAD_ERROR_400_2("502400", "文件格式不正确"),
    UPLOAD_ERROR_500("500500", "文件上传失败，请稍后重试"),

    // 附件下载
    DOWNLOAD_SUCCESS_200("600200", "下载文件成功"),
    DOWNLOAD_ERROR_400_1("601400", "文件aid不能为空"),
    DOWNLOAD_ERROR_500("600500", "文件下载失败，请稍后重试"),

    // 系统信息 SystemInfo
    SYSTEM_INFO_SUCCESS_200("700200", "获得系统信息成功"),
    SYSTEM_INFO_SUCCESS_200_1("701200", "新增系统信息成功"),
    SYSTEM_INFO_SUCCESS_200_2("702200", "删除系统信息成功"),
    SYSTEM_INFO_SUCCESS_200_3("703200", "修改系统信息成功"),
    SYSTEM_INFO_ERROR_400_1("700401", "系统信息ID不能为空"),
    SYSTEM_INFO_ERROR_400_2("700402", "删除系统信息失败"),
    SYSTEM_INFO_ERROR_500("700500", "获得系统信息失败，请稍后再试");

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;
}
