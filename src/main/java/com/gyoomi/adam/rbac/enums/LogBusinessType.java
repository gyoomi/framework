package com.gyoomi.adam.rbac.enums;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/17 22:22
 */
public enum LogBusinessType {
    /**
     * 其他
     */
    OTHER("其他", 0),

    /**
     * 新增
     */
    INSERT("新增", 1),

    /**
     * 修改
     */
    UPDATE("修改", 2),

    /**
     * 删除
     */
    DELETE("删除", 3),

    /**
     * 授权
     */
    GRANT("授权", 4),

    /**
     * 导出
     */
    EXPORT("导出", 5),

    /**
     * 导入
     */
    IMPORT("导入", 6),

    /**
     * 强退
     */
    FORCE("强退", 7),

    /**
     * 生成代码
     */
    GENCODE("生成代码", 8),

    /**
     * 清空
     */
    CLEAN("清空", 9);

    private String name;

    private Integer value;

    LogBusinessType(String name, Integer value) {
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
