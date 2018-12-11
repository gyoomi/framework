/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.service;

import com.cherry.framework.exception.BusinessException;
import com.cherry.framework.model.LogEntity;

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
