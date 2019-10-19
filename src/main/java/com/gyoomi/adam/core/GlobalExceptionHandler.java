package com.gyoomi.adam.core;

import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.properties.AdamProperties;
import com.gyoomi.adam.core.properties.AdamStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/18 22:23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger lg = LoggerFactory.getLogger(ExceptionHandler.class);

    @Autowired
    private AdamProperties adamProperties;

    public GlobalExceptionHandler() {}

    @ExceptionHandler(value = BusinessException.class)
    public Object handleBusinessException(HttpServletRequest req, BusinessException ex) {
        lg.error("业务错误 ", ex);
        return Response.builder().code(ex.getCode()).msg(ex.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    public Object handleException(HttpServletRequest req, Exception ex) {
        lg.error("系统错误 ", ex);
        int code = 500;
        String msg = "系统繁忙，请稍后重试！";
        if (AdamStatus.DEV.equals(adamProperties.getStatus())) {
            msg = ex.toString();
        }
        return Response.builder().code(code).msg(msg).build();
    }
}
