/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/9/18 9:23
 */
public abstract class BasePeriodThread extends BaseThread {

    private static Logger lg = LoggerFactory.getLogger(BasePeriodThread.class);
    private static boolean exitNow = false;
    private long waitSeconds = 60L;

    public BasePeriodThread(String name) {
        super(name);
        this.waitSeconds = this.getWaitSeconds();
    }

    @Override
    public void stopService() {
        exitNow = true;
        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public void run() {
        lg.info("后台服务【{}】启动就绪！", this.getName());
        try {
            while (!exitNow) {
                long start = System.currentTimeMillis();
                this.doWork();
                lg.debug("【{}】任务执行时间：【{}】ms", this.getName(), System.currentTimeMillis() - start);
                synchronized (this) {
                    this.wait(this.waitSeconds * 1000L);
                }
            }
        } catch (Exception e) {
            lg.error("后台服务【" + this.getName() + "】运行错误", e);
            try {
                synchronized (this) {
                    this.wait(60000L);
                }
            } catch (InterruptedException e1) {
                ;
            }
        }
        lg.info("后台服务【{}】结束运行！", this.getName());
    }

    public abstract long getWaitSeconds();
    public abstract void doWork() throws Exception;
}
