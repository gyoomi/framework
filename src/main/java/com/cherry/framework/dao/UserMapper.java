/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.dao;

import com.cherry.framework.model.UserEntity;

import java.util.List;

/**
 * 接口功能描述
 *
 * @author Leon
 * @version 2018/6/14 16:33
 */
public interface UserMapper {

    /**
     * 新增
     *
     * @param userEntity
     * @return
     */
    int save(UserEntity userEntity);

    /**
     * 查所有
     *
     * @return
     */
    List<UserEntity> findAllUserList();

}
