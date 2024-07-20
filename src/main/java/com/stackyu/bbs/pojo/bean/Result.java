package com.stackyu.bbs.pojo.bean;

import lombok.Data;

import java.util.Date;

/**
 * 请求返回类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class Result {
    private String message;
    private String code;
    private String exceptionMessage;
    private Integer error = 0;
    private Date time = new Date();
    private Object data;

    public Result() {
    }

    public Result(String message, String code, Integer error, Object data) {
        this.message = message;
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public Result(ResultCode resultCode, Integer error, Object data) {
        this.message = resultCode.getMessage();
        this.code = resultCode.getCode();
        this.error = error;
        this.data = data;
    }

    public static Result success(ResultCode resultCode) {
        Result result = new Result();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        return result;
    }

    public static Result success(ResultCode resultCode, Object t) {
        Result result = new Result();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        result.setError(0);
        result.setData(t);
        return result;
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        result.setError(1);
        return result;
    }

    public static Result error(ResultCode resultCode, Exception e) {
        Result result = new Result();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        result.setError(1);
        result.setExceptionMessage(e.getMessage());
        return result;
    }

}
