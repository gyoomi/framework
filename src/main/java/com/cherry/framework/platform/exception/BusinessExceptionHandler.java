/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理器
 *
 * @author Leon
 * @version 2018/7/5 22:42
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorInfo handleBusinessException(HttpServletRequest req, BusinessException ex) {
        ErrorInfo error = new ErrorInfo();
        error.setCode(ex.getCode());
        error.setMessage(ex.getMessage());
        error.setUrl(req.getRequestURL().toString());
        return error;
    }
}
