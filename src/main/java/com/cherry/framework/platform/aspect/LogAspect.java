/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.aspect;

import com.alibaba.fastjson.JSON;
import com.cherry.framework.platform.jwt.JwtUser;
import com.cherry.framework.platform.model.LogEntity;
import com.cherry.framework.platform.model.UserEntity;
import com.cherry.framework.platform.service.LogService;
import com.cherry.framework.platform.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:11
 */
//@Order(value = Integer.MAX_VALUE)
//@Aspect
//@Component
public class LogAspect {

    private static final Logger lg = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<Long> time = new ThreadLocal<>();

    @Autowired
    private LogService logService;


    @Pointcut(value = "@annotation(log)")
    public void logAnnotation(Log log) {}

    @Pointcut(value = "execution(public * com.cherry.framework.platform.controller..*.*(..))")
    public void webHandle() {}


    @Before(value = "webHandle()")
    public void before() {
        time.set(System.currentTimeMillis());
    }

    @Around(value = "logAnnotation(log)")
    public Object doLog(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object obj = joinPoint.proceed();
        writeSystemLog(joinPoint, req, log);
        return obj;
    }

    @AfterReturning(value = "webHandle()")
    public void afterReturn(JoinPoint joinPoint) {
        lg.debug("方法【{}】执行时间：【{}】ms", joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName(), System.currentTimeMillis() - time.get());
        time.remove();
    }

    private void writeSystemLog(ProceedingJoinPoint joinPoint, HttpServletRequest req, Log log) throws Exception {
        JwtUser jwtUser = (JwtUser) req.getAttribute("currentUser");
        UserEntity currentUser = null;
        LogEntity logEntity = new LogEntity();
        if (null != jwtUser) {
            currentUser = jwtUser.getUserEntity();
        }
        if (null != currentUser) {
            logEntity.setUserName(currentUser.getUserName());
            logEntity.setUserId(currentUser.getId());
        }
        logEntity.setContent(log.content());
        logEntity.setParameters(JSON.toJSONString(joinPoint.getArgs()));
        logEntity.setIpAddress(IPUtils.getIpAddr(req));
        logEntity.setCreateDate(new Date());
        logEntity.setRemark(log.remark());
        logService.save(logEntity);
    }
}
