package com.xxx.common.utils;
import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String msg;



    private T data;

    public Result(ResultCode code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

    public Result(){

    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultCode error) {
    }


    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
