/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/6 16:55
 */
@Component
public class BusinessExceptionBuilder extends Exception {

    @Autowired
    Environment env;

    public BusinessException build(int code) {
        String message = env.getProperty(String.valueOf(code));
        return new BusinessException(code, message);
    }
}
