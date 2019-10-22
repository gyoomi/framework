/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyoomi.adam.core.BusinessException;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.properties.AdamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping(value = "/interface/select")
    public Response select() {
        return Response.builder()
                .code(HttpServletResponse.SC_OK)
                .data(CHERRY.SPRING_CONTEXT.getBean(ExampleMapper.class).selectById("1185122568228818946"))
                .build();
    }

    @GetMapping(value = "/test-error/{num}")
    public Response testError(@PathVariable int num) throws Exception {
        if (num <= 10) {
           int s = 10 / num;
        } else {
            throw new BusinessException(500, "数字小于，请检查后进行提交");
        }
        return Response.builder()
                .code(HttpServletResponse.SC_OK)
                .data(CHERRY.SPRING_CONTEXT.getBean(ExampleMapper.class).selectById("1185122568228818946"))
                .build();
    }

    @GetMapping(value = "/redis")
    public Response testRedis() throws Exception {
        Example example = new Example();
        example.setCreateDate(new Date());
        example.setName("test name");
        CHERRY.SPRING_CONTEXT.getBean(StringRedisTemplate.class).opsForValue().set("test-redis", new ObjectMapper().writeValueAsString(example));

        return Response.builder()
                .code(HttpServletResponse.SC_OK)
                .data("ok")
                .build();
    }


    @GetMapping(value = "/interface/paths")
    public void testExclusivePath() {
        AdamProperties ad = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        System.out.println(ad.getSecurity().getExclusivePath());
    }
}
