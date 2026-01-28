package com.fan.common;

public enum ResultEnum {
        SUCCESS("200", "操作成功"),
        FAIL("400", "操作失败"),
        UNAUTHORIZED("401", "未认证"),
        FORBIDDEN("403", "禁止访问"),
        NOT_FOUND("404", "资源不存在"),
        INTERNAL_SERVER_ERROR("500", "服务器内部错误");
    ;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
