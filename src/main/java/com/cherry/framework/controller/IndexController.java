/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.controller;

import com.cherry.framework.constant.ExceptionConstant;
import com.cherry.framework.constant.JWTConstant;
import com.cherry.framework.exception.BusinessException;
import com.cherry.framework.exception.BusinessExceptionBuilder;
import com.cherry.framework.model.UserEntity;
import com.cherry.framework.service.UserService;
import com.cherry.framework.thread.FrameWorkMailSender;
import com.cherry.framework.thread.Mail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "首页", tags = "首页")
public class IndexController extends BaseController {

    private static final Logger lg = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    JWTConstant jwtConstant;

    @Autowired
    BusinessExceptionBuilder beb;

    @Autowired
    UserService userService;

    @Autowired
    FrameWorkMailSender mailSender;

    /**
     *  注册
     *
     * @param userEntity
     * @throws BusinessException
     */
    @PostMapping(value = "${jwt.jwt_route_register}")
    @ApiOperation(value = "用户注册")
    public UserEntity register(UserEntity userEntity) throws BusinessException {
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
     * @throws BusinessException
     */
    @PostMapping(value = "${jwt.jwt_route_authentication_path}")
    public String login(String loginName, String password) throws BusinessException {
        String token = userService.login(loginName, password);
        return token;
    }

    /**
     * 刷新口令
     *
     * @param req
     * @return
     * @throws BusinessException
     */
    @PostMapping(value = "${jwt.jwt_route_refreshtoken_path}")
    public String refresh(HttpServletRequest req) throws BusinessException {
        String token = req.getHeader(jwtConstant.JWT_HEADERS);
        if (StringUtils.isNotBlank(token)) {
            return userService.refresh(token);
        }
        throw new RuntimeException("");
    }

    @GetMapping(value = "/test")
    public ModelAndView test(HttpServletRequest req) {
        // UserEntity userEntity = getCurrentUser(req);
        UserEntity user = new UserEntity();
        user.setLoginName("tom");
        user.setId(234);
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
        mailSender.addTask(list);
    }

    @GetMapping(value = "/send2")
    @Retryable(value = {BusinessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2))
    public void testFile() throws BusinessException {
        System.out.println(new Date().toLocaleString());
        throw beb.build(ExceptionConstant.ERROR_CODE_10000);
    }

    @GetMapping(value = "/stop")
    public void testStop() {
        mailSender.stopService();
    }
}
