/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.service;

import com.cherry.framework.platform.exception.BusinessException;
import com.cherry.framework.platform.model.UserEntity;

/**
 * 系统用户    Service
 *
 * @author Leon
 * @version 2018/7/15 15:02
 */
public interface UserService {

    /**
     * 根据登录用户名查找登录用户
     *
     * @param loginName
     * @return
     * @throws BusinessException
     */
    UserEntity findUserByLoginName(String loginName) throws BusinessException;

    /**
     *  保存用户
     *
     * @param userEntity
     * @return
     * @throws BusinessException
     */
    UserEntity save(UserEntity userEntity) throws BusinessException;

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     * @throws BusinessException
     */
    String login(String loginName, String password) throws BusinessException;

    /**
     * 刷新口令
     *
     * @param oldToken
     * @return
     * @throws BusinessException
     */
    String refresh(String oldToken) throws BusinessException;
}
