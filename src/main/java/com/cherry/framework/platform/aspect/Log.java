/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */


package com.cherry.framework.platform.aspect;

import java.lang.annotation.*;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String content();
    String remark() default "";
}
