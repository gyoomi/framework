/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.core.thread;

/**
 * 调度基类
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
