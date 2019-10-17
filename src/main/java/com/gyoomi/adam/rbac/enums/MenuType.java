package com.gyoomi.adam.rbac.enums;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/4/7 17:24
 */
public enum MenuType {

    CATALOG("目录", "C"),
    MENU("菜单", "M"),
    BUTTON("按钮", "B");

    private String name;

    private String value;

    MenuType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
