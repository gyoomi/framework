package com.gyoomi.adam.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseService
 *
 * @author Leon
 * @version 2019/3/15 23:39
 */
public abstract class BaseService {

    private static final Logger lg = LoggerFactory.getLogger(BaseService.class);

    public BaseService() {}

    /**
     * 获取Spring Bean
     *
     * @param clazz 类
     * @param <T> 泛型
     * @return 容器中的实例Bean
     */
    private static <T> T getSpringBean(Class<T> clazz) {
        return CHERRY.SPRING_CONTEXT.getBean(clazz);
    }

}
