package com.cherry.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统配置基类
 *
 * @author Leon
 * @version 2018/5/23 14:40
 */
@SpringBootApplication
@MapperScan(value = "com.cherry.framework.dao")
@ComponentScan(basePackages = "com.cherry.framework")
public class FrameworkApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("Framework start");
        SpringApplication.run(FrameworkApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FrameworkApplication.class);
    }
}
