/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-17 14:59
 */
@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;


    @GetMapping(value = "/index")
    public String test() {
        User user = new User();
        user.setNickName("test昵称！！");
        userMapper.insert(user);
        return "hello framework 2.0 ";
    }

    @GetMapping(value = "/select")
    public User select() {
        return userMapper.selectById("1185122568228818946");
    }
}
