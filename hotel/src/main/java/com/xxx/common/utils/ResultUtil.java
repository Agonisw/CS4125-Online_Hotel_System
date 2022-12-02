package com.xxx.common.utils;

public class ResultUtil {

    public static <T> Result<T> success(ResultCode code, T data) {
        return new Result<T>(code.getCode(),code.getMsg(),data);
    }
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS,data);
    }
    public static <T> Result<T> error(T data) {
        return new Result<T>(ResultCode.ERROR, data);
    }

    public static <T> Result<T> error(ResultCode code) {
        return new Result<T>(code.getCode(),code.getMsg());
    }

    public static <T> Result<T> error(ResultCode code, T data) {
        return new Result<T>(code, data);
    }
}
