///**
// * Copyright © 2018, TaoDing
// * <p>
// * All Rights Reserved.
// */
//
//package com.cherry.framework.platform.mq;
//
//import org.apache.activemq.command.ActiveMQTopic;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.jms.Topic;
//
///**
// * 生产者
// *
// * @author Leon
// * @version 2018/9/14 16:17
// */
//@Component
//public class Producer {
//
//    private static final Logger lg = LoggerFactory.getLogger(Producer.class);
//
//    @Autowired
//    JmsMessagingTemplate jmsMessagingTemplate;
//
//    private Topic topic = new ActiveMQTopic("base.topic");
//
//    @Scheduled(fixedRate=6000,initialDelay=3000)
//    public void send01() {
//        lg.info("消息队列生产者1：生产pub/scr消息");
//        jmsMessagingTemplate.convertAndSend(topic, "消息队列生产者1：生产pub/scr消息");
//    }
//
//    @Scheduled(fixedRate=6000,initialDelay=3000)
//    public void send02() {
//        lg.info("消息队列生产者2：生产pub/scr消息");
//        jmsMessagingTemplate.convertAndSend(topic, "消息队列生产者2：生产pub/scr消息");
//    }
//}
