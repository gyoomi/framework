/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.example;

import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.properties.AdamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-17 14:59
 */
@RestController
public class ExampleController {

    @Autowired
    private AdamProperties adamProperties;

    @GetMapping(value = "prop")
    public void testProp() {
        System.out.println("----------------------------------");
        System.out.println(adamProperties.getAccessKey().getHeaderName());
        System.out.println(adamProperties.getAccessKey().getRequestName());
        System.out.println(adamProperties.getStatus());
        System.out.println(adamProperties.getVersion());
        System.out.println("----------------------------------");
    }

    @GetMapping(value = "/save")
    public String save() {
        Example example = new Example();
        example.setCreateDate(new Date());
        example.setName("test name");
        example.setRemark(LocalDateTime.now().toString());
        example.setTotal(11);
        CHERRY.SPRING_CONTEXT.getBean(ExampleMapper.class).insert(example);
        return "hello framework 2.0 " + LocalDateTime.now();
    }

    @GetMapping(value = "/select")
    public Example select() {
        return CHERRY.SPRING_CONTEXT.getBean(ExampleMapper.class).selectById("1185122568228818946");
    }
}
