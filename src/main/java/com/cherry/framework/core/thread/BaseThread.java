/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.core.thread;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/8/18 22:27
 */
public abstract class BaseThread extends Thread {

    BaseThread(String name) {
        this.setName(name);
    }

    public abstract void stopService();

}
