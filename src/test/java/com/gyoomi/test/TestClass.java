/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.test;

import com.gyoomi.adam.core.jwt.JwtTokenUtils;
import com.gyoomi.adam.core.jwt.JwtUser;
import com.gyoomi.adam.rbac.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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

    @Test
    public void testGetClaims() throws Exception {
        User user = new User();
        user.setGender(1);
        user.setCreateDate(new Date());
        user.setLoginName("admin");
        user.setNickName("超级管理员");
        user.setEmail("222@qq.com");
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUser(user);
        String token = JwtTokenUtils.createToken(jwtUser);
        System.out.println(token);
        String loginNameFromToken = JwtTokenUtils.getLoginNameFromToken(token);
        System.out.println(loginNameFromToken);
    }

    @Test
    public void testExpired() {
        User user = new User();
        user.setGender(1);
        user.setCreateDate(new Date());
        user.setLoginName("admin");
        user.setNickName("超级管理员");
        user.setEmail("222@qq.com");
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUser(user);

        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwaG9uZU51bWJlciI6bnVsbCwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJsb2dpbk5hbWUiOiJhZG1pbiIsImV4cCI6MTU3MTY0NTA1OSwiZW1haWwiOiIyMjJAcXEuY29tIiwiY3JlYXRlRGF0ZSI6MTU3MTY0MzI1OTQxNX0._aga3NsTBH9jy9qV5SKnSRsWj4AnMkt8_8vyjWUffzLk4kdzINRO7eaaAMET2iz2hnHlqjnVzkAKxfe-Bs1YSA";
        System.out.println(JwtTokenUtils.valid(token, jwtUser));
        System.out.println("-----------------------------------");
        String token2 = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwaG9uZU51bWJlciI6bnVsbCwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJsb2dpbk5hbWUiOiJhZG1pbiIsImV4cCI6MTU3MTY1MTMwNCwiZW1haWwiOiIyMjJAcXEuY29tIiwiY3JlYXRlRGF0ZSI6MTU3MTY0OTUwNDE4NH0.BJSaL5qVAUV9s4Nny1HiEFH6znCQcdF7ziaxOuqk70dW4IABjr1BPaGq7MHjusk-3hDfOX5jd-6zfh91VKvqdw";
        System.out.println(JwtTokenUtils.valid(token2, jwtUser));
        System.out.println("-----------------------------------");
        String token3 = "Bearer eyJhbGciOiJIUzUxMiJ9.e1yJwaG9uZU51bWJlciI6bnVsbCwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJsb2dpbk5hbWUiOiJhZG1pbiIsImV4cCI6MTU3MTY1MTMwNCwiZW1haWwiOiIyMjJAcXEuY29tIiwiY3JlYXRlRGF0ZSI6MTU3MTY0OTUwNDE4NH0.BJSaL5qVAUV9s4Nny1HiEFH6znCQcdF7ziaxOuqk70dW4IABjr1BPaGq7MHjusk-3hDfOX5jd-6zfh91VKvqdw";
        System.out.println(JwtTokenUtils.valid(token3, jwtUser));
    }
}
