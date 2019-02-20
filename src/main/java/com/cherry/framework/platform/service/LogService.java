/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.service;

import com.cherry.framework.platform.exception.BusinessException;
import com.cherry.framework.platform.model.LogEntity;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:37
 */
public interface LogService {

    /**
     * 保存日志
     *
     * @param logEntity
     * @return
     * @throws BusinessException
     */
    LogEntity save(LogEntity logEntity) throws BusinessException;
}
