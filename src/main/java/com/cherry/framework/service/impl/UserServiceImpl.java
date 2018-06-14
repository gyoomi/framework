/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.service.impl;

import com.cherry.framework.dao.UserMapper;
import com.cherry.framework.model.UserEntity;
import com.cherry.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User ServiceImpl
 *
 * @author Leon
 * @version 2018/6/14 17:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 新增
     *
     * @param userEntity
     * @return
     */
    @Override
    @Transactional
    public int save(UserEntity userEntity) {
        return userMapper.save(userEntity);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<UserEntity> findAllUserList() {
        return userMapper.findAllUserList();
    }
}
