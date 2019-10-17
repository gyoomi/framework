package com.gyoomi.adam.rbac.enums;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/17 22:22
 */
public enum LogOperatorType {
    /**
     * 其他
     */
    OTHER("其他", 0),

    /**
     * 后台管理
     */
    MANAGE("后台管理", 1),

    /**
     * 手机端
     */
    MOBILE("手机端", 2);

    private String name;

    private Integer value;

    LogOperatorType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
