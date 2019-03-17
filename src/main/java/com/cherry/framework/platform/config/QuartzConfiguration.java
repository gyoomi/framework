///**
// * Copyright © 2018, TaoDing
// * <p>
// * All Rights Reserved.
// */
//
//package com.cherry.framework.platform.config;
//
//import com.cherry.framework.platform.job.MyCronJob;
//import com.cherry.framework.platform.job.MyJob;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//
///**
// * 定时任务配置
// *
// * @author Leon
// * @version 2018/9/12 11:41
// */
//// @Configuration
//public class QuartzConfiguration {
//
//    @Bean
//    public JobDetail myJobDetail() {
//        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger myJobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(15).repeatForever();
//
//        return TriggerBuilder.newTrigger()
//                .forJob(myJobDetail())
//                .withIdentity("myJobTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//    @Bean
//    public JobDetail myCronJobDetail() {
//        return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger CronJobTrigger() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//
//        return TriggerBuilder.newTrigger()
//                .forJob(myCronJobDetail())
//                .withIdentity("myCronJobTrigger")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//    }
//}
