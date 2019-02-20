/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.exception;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/6 10:10
 */
public class ErrorInfo {

    public static final Integer OK = 200;

    public static final Integer ERROR = 500;

    private int code;
    private String message;
    private String url;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
