package com.xjh.http;

/**
 * 枚举的高级用法，封装示例
 * Created by xjh on 2018/1/31.
 */

public enum HttpStatus {

    /** 该枚举值调用了HttpStatus的构造方法*/
    SUCCESS(200,"Success");

    private int mCode;
    private String mMessage;
    HttpStatus(int code, String message){
        this.mCode = code;
        this.mMessage = message;
    }

    public boolean isSuccess() {
        int value = mCode / 100;
        if (value == 2) {
            return true;
        }
        return false;
    }

    /**
     * 获取枚举值
     * @param code 传入状态码
     * @return
     */
    public static HttpStatus getValue(int code) {
        for (HttpStatus httpStatus : values()) {
            if (code == httpStatus.mCode) {
                return httpStatus;
            }
        }
        return null;
    }
}
