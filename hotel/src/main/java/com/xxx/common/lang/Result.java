package com.xxx.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * Uniformly encapsulate data result sets
 */
@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    //Successful result set
    public static Result succ(Object data){
        return succ(200,"operate successfully",data);
    }
    public static Result succ(int code,String msg,Object data){
        Result r=new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    //Failure result set
    public static Result fail(String msg){
        return succ(400,msg,null);
    }
    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }
    public static Result fail(int code,String msg,Object data){
        Result r=new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
