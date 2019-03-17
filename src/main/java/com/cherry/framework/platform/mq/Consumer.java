/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author Leon
 * @version 2018/9/14 16:21
 */
//@Component
//public class Consumer {
//
//    private static final Logger lg = LoggerFactory.getLogger(Consumer.class);
//
//    @JmsListener(destination = "base.topic")
//    public void consume01(String text) {
//        lg.info("消费者1：消费了【{}】", text);
//    }
//
//    @JmsListener(destination = "base.topic")
//    public void consume02(String text) {
//        lg.info("消费者2：消费了【{}】", text);
//    }
//}
