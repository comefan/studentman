package com.fan.exception;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/31 19:41
 */
public class CustomException extends RuntimeException{
    private String msg;
    public CustomException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
