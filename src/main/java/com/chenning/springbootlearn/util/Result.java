package com.chenning.springbootlearn.util;

import lombok.Data;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/2 15:33
 */
@Data
public class Result<T> {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private T content;// 返回数据

    public Result() {
    }

    public Result(boolean flag, String message, T content) {
        this.flag = flag;
        this.message = message;
        this.content = content;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, T content) {
        this(flag, code, message);
        this.content = content;
    }


    public static <T> Result ok() {
        return new Result<T>(true, 200, "success");
    }

    public static <T> Result ok(T content) {
        return new Result<T>(true, 200, "success", content);
    }

    public static <T> Result error(String message) {
        return new Result<T>(false, 400, message);
    }

    public static <T> Result error(String message, Integer code) {
        return new Result<T>(false, code, message);
    }

    public static <T> Result error() {
        return new Result<T>(false, 400, "error");
    }
}
