package com.luluy233.maotaitraceability.vo;

/**
 * 状态码和状态信息
 */
public enum ErrorCode {
    // 错误码
    SYSTEM_ERROR(-9999, "系统内部异常"),
    PARAMS_ERROR(1001, "参数有误"),
    TYPE_ERROR(1002, "类型有误"),
    USR_PWD_NOT_EXIST(1003, "用户名不存在或密码错误"),
    TOKEN_ERROR(1004, "token不合法"),
    USR_EXIT(1005, "账户已被注册"),
    NO_PERMISSION(1006, "无访问权限"),
    PWD_ERROR(1007, "密码错误"),
    TRACEABLE_CODE_EXIT(1008,"药品溯源编码已存在"),
    ;

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
