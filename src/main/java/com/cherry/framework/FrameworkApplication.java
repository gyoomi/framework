package com.cherry.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

/**
 * 系统配置基类
 *
 * @author Leon
 * @version 2018/5/23 14:40
 */
@EnableRetry
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
public class FrameworkApplication extends SpringBootServletInitializer {

    private static final Logger lg = LoggerFactory.getLogger(FrameworkApplication.class);

    public static void main(String[] args) {
        lg.info("Framework start at 【{}】", LocalDateTime.now());
        SpringApplication.run(FrameworkApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FrameworkApplication.class);
    }
}
