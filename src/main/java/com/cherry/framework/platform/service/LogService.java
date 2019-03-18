package com.cherry.framework.platform.service;

import com.cherry.framework.core.BaseService;
import com.cherry.framework.platform.model.LogEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:37
 */
@Service
public class LogService extends BaseService {


    /**
     * 保存日志
     *
     * @param logEntity
     * @return
     * @throws Exception
     */
    public LogEntity save(LogEntity logEntity) throws Exception {
        logEntity.setCreateDate(new Date());
        Long id = insertIntoTable(logEntity);
        logEntity.setId(id);
        return logEntity;
    }
}
