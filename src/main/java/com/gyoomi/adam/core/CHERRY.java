package com.gyoomi.adam.core;

import org.springframework.context.ApplicationContext;

import java.time.format.DateTimeFormatter;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/17 11:47
 */
public class CHERRY {

    /**
     * Spring ApplicationContext
     */
    public static ApplicationContext SPRING_CONTEXT = null;

    //=================================================================
    //		默认日期、时间格式
    //=================================================================

    /**
     * 日期格式
     */
    public final static String PATTERN_DATE = "yyyy-MM-dd";

    public final static DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern(CHERRY.PATTERN_DATE);
    public final static DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(CHERRY.PATTERN_DATE_TIME);

    /**
     * 时间格式
     */
    public final static String PATTERN_TIME = "HH:mm:ss";

    /**
     * 日期时间格式
     */
    public final static String PATTERN_DATE_TIME = PATTERN_DATE + " " + PATTERN_TIME;

    public CHERRY() {}
}
