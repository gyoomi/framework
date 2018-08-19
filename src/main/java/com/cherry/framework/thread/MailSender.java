/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.thread;

import org.springframework.stereotype.Component;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/8/18 23:40
 */
@Component
public class MailSender extends BaseTaskEventThread<Mail> {

    public MailSender() {
        super("邮件发送服务");
    }

    @Override
    public short getWorkers() {
        return 2;
    }

    @Override
    public void doWork(Mail task) throws Exception {
        System.out.println("向当前用户" + task.getSendUser() + " 发送邮件。标题是：" + task.getTitle());
        Thread.sleep(2000);
        System.out.println("发送完毕！！！");
    }
}
