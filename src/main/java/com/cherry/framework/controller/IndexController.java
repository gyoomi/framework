/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.controller;

import com.cherry.framework.constant.JWTConstant;
import com.cherry.framework.exception.BusinessException;
import com.cherry.framework.exception.BusinessExceptionBuilder;
import com.cherry.framework.model.UserEntity;
import com.cherry.framework.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 类功能描述
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
    BusinessExceptionBuilder beb;

    @Autowired
    UserService userService;

    /**
     *  注册
     *
     * @param userEntity
     * @throws BusinessException
     */
    @PostMapping(value = "${jwt.jwt_route_register}")
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
    @PostMapping(value = "/${jwt.jwt_route_authentication_path}")
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

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }
}
