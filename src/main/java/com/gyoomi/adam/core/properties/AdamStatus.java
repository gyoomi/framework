package com.gyoomi.adam.core.properties;

/**
 * Adam Status
 *
 * @author Leon
 * @version 2019/10/18 22:05
 */
public enum AdamStatus {

    /**
     * 开发
     */
    DEV(0),

    /**
     * 测试
     */
    TEST(1),

    /**
     * 预发布
     */
    PRE(2),

    /**
     * 生产环境
     */
    PRODUCT(3);

    private final int key;

    AdamStatus(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
