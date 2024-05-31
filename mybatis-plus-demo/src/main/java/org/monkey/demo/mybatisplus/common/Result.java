package org.monkey.demo.mybatisplus.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Result<T> {
    /** 统一请求id */
    private String reqId;

    /** 返回码 */
    private String code;

    /** 消息内容 */
    private String msg;

    /** 响应数据 */
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(Constants.SUCCESS, Constants.SUCCESS_MSG, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(Constants.SUCCESS, Constants.SUCCESS_MSG, data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(Constants.SUCCESS, msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(Constants.SUCCESS, msg, data);
    }

    public static <T> Result<T> success(String code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(Constants.FAIL, Constants.FAIL_MSG, null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(Constants.FAIL, Constants.FAIL_MSG, data);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(Constants.FAIL, msg, null);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(Constants.FAIL, msg, data);
    }

    public static <T> Result<T> fail(String code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public Result() {
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, T data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
