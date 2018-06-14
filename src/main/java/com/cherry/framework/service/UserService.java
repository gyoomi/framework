/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.service;

import com.cherry.framework.model.UserEntity;

import java.util.List;

/**
 * User Service
 *
 * @author Leon
 * @version 2018/6/14 17:11
 */
public interface UserService {

    /**
     * 新增
     *
     * @param userEntity
     * @return
     */
    int save(UserEntity userEntity);

    /**
     * 查询所有
     *
     * @return
     */
    List<UserEntity> findAllUserList();
}
