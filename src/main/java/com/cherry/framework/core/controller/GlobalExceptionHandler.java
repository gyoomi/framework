package com.cherry.framework.core.controller;

import com.cherry.framework.core.model.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/18 22:23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger lg = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {}

    @ExceptionHandler(value = BusinessException.class)
    public Object handleBusinessException(HttpServletRequest req, BusinessException ex) {
        HashMap<String, Object> e = new HashMap<>();
        e.put("code", ex.getCode());
        e.put("message", ex.getMessage());
        return e;
    }

    @ExceptionHandler(value = Exception.class)
    public Object handleException(HttpServletRequest req, Exception ex) {
        HashMap<String, Object> e = new HashMap<>();
        e.put("code", 500);
        e.put("message", ex.getMessage());
        return e;
    }
}
