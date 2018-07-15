/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.service;

import com.cherry.framework.exception.BusinessException;
import com.cherry.framework.model.UserEntity;

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
}
