///**
// * Copyright © 2018, TaoDing
// * <p>
// * All Rights Reserved.
// */
//
//package com.cherry.framework.platform.job;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.time.LocalDateTime;
//
///**
// * 我的任务
// *
// * @author Leon
// * @version 2018/9/12 11:33
// */
//public class MyJob extends QuartzJobBean {
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("start My Job：" + LocalDateTime.now());
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("end  My Job：" + LocalDateTime.now());
//
//    }
//}
