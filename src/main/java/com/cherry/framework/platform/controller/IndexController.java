/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.controller;

import com.cherry.framework.core.controller.BaseController;
import com.cherry.framework.core.model.BusinessException;
import com.cherry.framework.core.thread.Mail;
import com.cherry.framework.platform.constant.JWTConstant;
import com.cherry.framework.platform.model.UserEntity;
import com.cherry.framework.platform.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 首页   Controller
 *
 * @author Leon
 * @version 2018/6/13 21:53
 */
@RestController
public class IndexController extends BaseController {

    private static final Logger lg = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    JWTConstant jwtConstant;

    @Autowired
    UserService userService;


    /**
     *  注册
     *
     * @param userEntity
     * @throws Exception
     */
    @PostMapping(value = "${jwt.jwt_route_register}")
    public UserEntity register(UserEntity userEntity) throws Exception {
        userEntity.setCreateDate(new Date());
        userEntity.setStatus(1);
        userEntity.setBindType(1);
        UserEntity entity = userService.save(userEntity);
        entity.setPassword("");
        entity.setBindType(null);
        entity.setStatus(null);
        return entity;
    }

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping(value = "${jwt.jwt_route_authentication_path}")
    public String login(String loginName, String password) throws Exception {
        String token = userService.login(loginName, password);
        return token;
    }

    @GetMapping(value = "/test")
    public String test() throws Exception {
        return "hello framework";
    }

    /**
     * 刷新口令
     *
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "${jwt.jwt_route_refreshtoken_path}")
    public String refresh(HttpServletRequest req) throws Exception {
        String token = req.getHeader(jwtConstant.JWT_HEADERS);
        if (StringUtils.isNotBlank(token)) {
            return userService.refresh(token);
        }
        throw new RuntimeException("");
    }

    @GetMapping(value = "/test11")
    public ModelAndView test(HttpServletRequest req) {
        // UserEntity userEntity = getCurrentUser(req);
        UserEntity user = new UserEntity();
        user.setLoginName("tom");
        user.setBindType(1);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/user/show.html");
        return mv;
    }

    @GetMapping(value = "/send")
    public void testMail() {
        List<Mail> list = new ArrayList<>();
        Mail m = new Mail();
        m.setSendUser("张三");
        m.setTitle("会议通知");
        m.setContent("明天上午九点公司小会议召开项目立项会议！！");
        m.setTo("362808128@qq.com");
        list.add(m);
        Mail m2 = new Mail();
        m2.setSendUser("李四");
        m2.setTitle("会议通知");
        m2.setContent("明天上午九点公司小会议召开项目立项会议！！！");
        m2.setTo("362808128@qq.com");
        list.add(m2);
        Mail m3 = new Mail();
        m3.setSendUser("王五");
        m3.setTitle("会议通知");
        m3.setContent("明天上午九点公司小会议召开项目立项会议！！！");
        m3.setTo("362808128@qq.com");
        list.add(m3);
        // mailSender.addTask(list);
    }

    @GetMapping(value = "/send2")
    @Retryable(value = {BusinessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2))
    public void testFile() throws BusinessException {
        System.out.println(new Date().toLocaleString());
        throw new BusinessException("服务器内部代码错误，请稍后重试");
    }

}
