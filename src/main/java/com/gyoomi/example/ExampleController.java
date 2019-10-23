/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyoomi.adam.core.BaseController;
import com.gyoomi.adam.core.BusinessException;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.properties.AdamProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 示例 Controller
 *
 * @author Leon
 * @date 2019-10-17 14:59
 */
@RestController
public class ExampleController extends BaseController {

    private static final Logger lg = LoggerFactory.getLogger(ExampleController.class);


    /**
     * props
     *
     * @return
     */
    @GetMapping(value = "/interface/props")
    public String testProp() {
        System.out.println("----------------------------------");
        System.out.println(getSpringBean(AdamProperties.class).getAccessKey().getHeaderName());
        System.out.println(getSpringBean(AdamProperties.class).getAccessKey().getRequestName());
        System.out.println(getSpringBean(AdamProperties.class).getStatus());
        System.out.println(getSpringBean(AdamProperties.class).getVersion());
        System.out.println("----------------------------------");
        return "ok at " + LocalDateTime.now().toString();
    }

    /**
     * save
     *
     * @return
     */
    @PostMapping(value = "/api/example")
    public Response save(Example example) {
        example.setCreateDate(new Date());
        example.setRemark(LocalDateTime.now().toString());
        example.setTotal(11);
        CHERRY.SPRING_CONTEXT.getBean(ExampleMapper.class).insert(example);

        return Response.builder()
                .code(HttpServletResponse.SC_OK)
                .build();
    }

    @GetMapping(value = "/api/example/{id}")
    public Response select(@PathVariable String id) {

        return Response.builder()
                .code(HttpServletResponse.SC_OK)
                .data(CHERRY.SPRING_CONTEXT.getBean(ExampleMapper.class).selectById(id))
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

    @GetMapping(value = "/interface/redis")
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


    @GetMapping(value = "/api/paths")
    public void testExclusivePath() {
        AdamProperties ad = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        System.out.println(ad.getSecurity().getExclusivePath());
    }
}
