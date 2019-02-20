package com.cherry.framework.core.model;

import java.io.Serializable;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 22:48
 */
public class DBMappingProperty implements Serializable {

    private String name;

    private Class<?> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
