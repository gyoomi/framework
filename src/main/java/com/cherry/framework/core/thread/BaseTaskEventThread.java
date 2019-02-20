/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/8/18 22:28
 */
public abstract class BaseTaskEventThread<T> extends BaseThread {

    private static final Logger lg = LoggerFactory.getLogger(BaseTaskEventThread.class);
    private String serviceName;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition hasTask;
    private static boolean exitNow = false;
    private List<T> tasks = new ArrayList<>();

    public BaseTaskEventThread(String name) {
        super(name);
        this.serviceName = name;
        short workers = this.getWorkers();
        for	(int i = 0; i < workers; i++) {
            Worker worker = new Worker("Worker-" + String.valueOf(i));
            worker.start();
        }
    }

    @Override
    public void stopService() {
        exitNow = true;
        lock.lock();
        try {
            hasTask.signalAll();
            synchronized (this) {
                this.notify();
            }
        } finally {
            lock.unlock();
        }
    }

    public void addTask(T task) {
        lock.lock();
        try {
            this.tasks.add(task);
            lg.info("接到1个新任务【{}】", this.serviceName);
            hasTask.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void addTask(List<T> tasks) {
        lock.lock();
        try {
            this.tasks.addAll(tasks);
            lg.info("接到{}个新任务【{}】", tasks.size(), this.serviceName);
            hasTask.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T getTask() {
        lock.lock();
        try {
            T task;
            while (0 == this.tasks.size()) {
                if (exitNow) {
                    task = null;
                    return task;
                }
                lg.info("等待新任务【{}】", this.serviceName);
                hasTask.await();
            }
            task = this.tasks.get(0);
            this.tasks.remove(0);
            return task;
        } catch (InterruptedException e) {
            lg.error("工作任务获取错误【{}】", this.serviceName, e);
            return null;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        lg.info("后台服务【{}】启动就绪！", this.serviceName);
        while (!exitNow) {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (Exception e) {
                lg.error("调度线程运行错误【" + this.serviceName + "】：", e);
            }
        }
        lg.info("后台服务【{}】结束运行！", this.serviceName);
    }

    public abstract short getWorkers();

    public abstract void doWork(T task) throws Exception;

    static {
        hasTask = lock.newCondition();
    }

    class Worker extends Thread {
        public Worker(String name) {
            this.setName(name);
            BaseTaskEventThread.lg.info("{}【{}】启动开始工作！", this.getName(), BaseTaskEventThread.this.serviceName);
        }

        @Override
        public void run() {
            while (!BaseTaskEventThread.exitNow) {
                try {
                    T task = BaseTaskEventThread.this.getTask();
                    if (null != task) {
                        BaseTaskEventThread.this.doWork(task);
                    }
                } catch (Exception e) {
                    BaseTaskEventThread.lg.error("执行任务失败【" + BaseTaskEventThread.this.serviceName + "】：", e);
                }
            }
            BaseTaskEventThread.lg.info("{}【{}】结束工作！", this.getName(), BaseTaskEventThread.this.serviceName);
        }
    }

}
