/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 自定义异常
 *
 * @author Leon
 * @version 2018/7/5 21:54
 */
public class BusinessException extends Exception {

    /**
     * 业务错误码
     */
    private int code;

    /**
     * 自定义错误信息
     */
    private String message;

    /**
     * 错误异常
     */
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public BusinessException() {
        super();
    }

    public BusinessException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code) {
        super();
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
