package com.cherry.framework.core.model;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/18 22:21
 */
public class BusinessException extends Exception {

    /**
     * 业务错误码
     */
    private int code;


    public BusinessException() {
        super();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }


    public BusinessException(int code) {
        super();
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
