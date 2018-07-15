/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.service.impl;

import com.cherry.framework.dao.UserEntityMapper;
import com.cherry.framework.exception.BusinessException;
import com.cherry.framework.model.UserEntity;
import com.cherry.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 根据登录用户名查找登录用户
     *
     * @param loginName
     * @return
     * @throws BusinessException
     */
    @Override
    public UserEntity findUserByLoginName(String loginName) throws BusinessException {
        UserEntity userEntity = null;
        Optional<String> nameOpt = Optional.ofNullable(loginName);
        if (nameOpt.isPresent()) {
            userEntity = nameOpt.map(userEntityMapper::findUserByLoginName).orElse(null);
        }
        return userEntity;
    }
}
