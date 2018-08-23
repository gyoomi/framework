package com.cherry.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(value = "com.cherry.framework.dao")
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
