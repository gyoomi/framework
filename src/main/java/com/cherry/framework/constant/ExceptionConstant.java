/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.constant;

import org.springframework.context.annotation.Configuration;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/6 10:39
 */
@Configuration
public class ExceptionConstant {

    /**
     * 服务器内部代码错误，请稍后重试
     */
    public static final int ERROR_CODE_10000 = 10000;

    /**
     * 用户名或密码不能为空
     *
     */
    public static final int ERROR_CODE_10001 = 10001;

    /**
     * 用户不存在，请联系管理员
     *
     */
    public static final int ERROR_CODE_10002 = 10002;

    /**
     * 密码错误
     *
     */
    public static final int ERROR_CODE_10003 = 10003;

}
