package me.wangxiaodong.hrm.base;

import org.springframework.http.HttpStatus;

public enum RespCode {

    SUCCESS(HttpStatus.OK.value(), "请求成功"),
    FAIL(HttpStatus.NO_CONTENT.value(), "网络异常，请稍后重试"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(),"未授权,请登录");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
