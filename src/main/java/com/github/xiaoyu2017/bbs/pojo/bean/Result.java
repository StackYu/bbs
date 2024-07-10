package com.github.xiaoyu2017.bbs.pojo.bean;

import lombok.Data;

import java.util.Date;

/**
 * 请求返回类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class Result<T> {
    private String message;
    private String code;
    private Integer error = 0;
    private T data;
    private Date time = new Date();

    public Result() {
    }

    public Result(String message, String code, Integer error, T data) {
        this.message = message;
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public Result(ResultCode resultCode, Integer error, T data) {
        this.message = resultCode.getMessage();
        this.code = resultCode.getCode();
        this.error = error;
        this.data = data;
    }

    public static Result<String> success(ResultCode resultCode) {
        Result<String> result = new Result<>();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        return result;
    }

    public static Result<String> error(ResultCode resultCode) {
        Result<String> result = new Result<>();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        result.setError(1);
        return result;
    }

    public static <T> Result<T> success(ResultCode resultCode, T t) {
        Result<T> result = new Result<>();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        result.setError(1);
        result.setData(t);
        return result;
    }
}
