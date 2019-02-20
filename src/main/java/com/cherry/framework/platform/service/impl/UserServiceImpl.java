/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.service.impl;

import com.cherry.framework.platform.aspect.Log;
import com.cherry.framework.platform.constant.ExceptionConstant;
import com.cherry.framework.platform.constant.JWTConstant;
import com.cherry.framework.platform.dao.UserEntityMapper;
import com.cherry.framework.platform.exception.BusinessException;
import com.cherry.framework.platform.exception.BusinessExceptionBuilder;
import com.cherry.framework.platform.jwt.JwtTokenUtil;
import com.cherry.framework.platform.jwt.JwtUser;
import com.cherry.framework.platform.model.UserEntity;
import com.cherry.framework.platform.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/15 15:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTConstant jwtConstant;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BusinessExceptionBuilder beb;

    /**
     * 根据登录用户名查找登录用户
     *
     * @param loginName
     * @return
     * @throws BusinessException
     */
    @Override
    @Log(content = "根据用户查询用户")
    public UserEntity findUserByLoginName(String loginName) throws BusinessException {
        UserEntity userEntity = null;
        Optional<String> nameOpt = Optional.ofNullable(loginName);
        if (nameOpt.isPresent()) {
            userEntity = nameOpt.map(userEntityMapper::findUserByLoginName).orElse(null);
        }
        return userEntity;
    }

    /**
     * 保存用户
     *
     * @param userEntity
     * @return
     * @throws BusinessException
     */
    @Override
    @Log(content = "保存用户")
    public UserEntity save(UserEntity userEntity) throws BusinessException {
        String loginName = userEntity.getLoginName();
        if (loginName != null) {
            UserEntity userToJudge= userEntityMapper.findUserByLoginName(loginName);
            if (userToJudge != null) {
                throw new RuntimeException("用户已存在");
            }
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityMapper.insert(userEntity);
        return userEntity;
    }

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     * @throws BusinessException
     */
    @Override
    @Log(content = "登录校验用户")
    public String login(String loginName, String password) throws BusinessException {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            throw beb.build(ExceptionConstant.ERROR_CODE_10001);
        }
        UserEntity userEntity = userEntityMapper.findUserByLoginName(loginName);
        if (userEntity == null) {
            throw beb.build(ExceptionConstant.ERROR_CODE_10002);
        }
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw beb.build(ExceptionConstant.ERROR_CODE_10003);
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginName, password);
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserEntity(userEntity);
        String token = jwtTokenUtil.createToken(jwtUser);
        return token;
    }

    /**
     * 刷新口令
     *
     * @param oldToken
     * @return
     * @throws BusinessException
     */
    @Override
    @Log(content = "刷新令牌")
    public String refresh(String oldToken) throws BusinessException {
        final String token = oldToken.substring((jwtConstant.JWT_TOKENHEAD + " ").length());
        String loginName = jwtTokenUtil.getLoginNameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(loginName);
        if (jwtTokenUtil.validatToken(token, jwtUser)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
