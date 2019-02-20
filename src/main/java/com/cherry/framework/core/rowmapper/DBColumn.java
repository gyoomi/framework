package com.cherry.framework.core.rowmapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 23:00
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumn {

    String name() default "";
    boolean isPrimaryKey() default false;
}
