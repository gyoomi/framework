/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.service.impl;

import com.cherry.framework.platform.dao.LogEntityMapper;
import com.cherry.framework.platform.exception.BusinessException;
import com.cherry.framework.platform.model.LogEntity;
import com.cherry.framework.platform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:37
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogEntityMapper logEntityMapper;

    /**
     * 保存日志
     *
     * @param logEntity
     * @return
     * @throws BusinessException
     */
    @Override
    public LogEntity save(LogEntity logEntity) throws BusinessException {
        logEntity.setCreateDate(new Date());
        logEntityMapper.insert(logEntity);
        return logEntity;
    }
}
