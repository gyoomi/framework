/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/15 21:41
 */
@Configuration
public class CommonConstant {

    @Value(value = "${server.servlet.context-path:#{''}}")
    public String SERVER_SERVLET_PATH;
}
