/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.core.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/8/18 23:40
 */
public class FrameWorkMailSender extends BaseTaskEventThread<Mail> {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public FrameWorkMailSender(String name) {
        super(name);
    }

    @Override
    public short getWorkers() {
        return 2;
    }

    @Override
    public void doWork(Mail task) throws Exception {
        if (task != null) {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(task.getTitle());
            mimeMessageHelper.setTo(task.getTo());
            mimeMessageHelper.setText(task.getContent());
            this.javaMailSender.send(mimeMessage);
        }
    }
}
