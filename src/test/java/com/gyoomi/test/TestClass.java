/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.test;

import com.gyoomi.adam.core.jwt.JwtTokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-17 15:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClass {

    @Test
    public void test() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", "root");
        claims.put("deptId", "1231313");
        claims.put("deptName", "部门名称");

        String token = JwtTokenUtils.createToken(claims);
        System.out.println(token);
    }
}
