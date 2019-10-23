/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.core.model;

import com.alibaba.fastjson.JSON;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.properties.AdamProperties;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-23 11:03
 */
@Data
public class UserSession {

    /**
     * jwt Token
     */
    private String token;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 资源url集合
     */
    private Set<String> urls = new HashSet<>();

    public static UserSession getUserSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = requestAttributes.getRequest();

        AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        String jwtToken = req.getHeader(adamProperties.getSecurity().getJwtToken().getHeaderName());
        if (StringUtils.isBlank(jwtToken)) {
            jwtToken = req.getParameter(adamProperties.getSecurity().getJwtToken().getRequestName());
        }
        jwtToken = jwtToken.replace(adamProperties.getSecurity().getJwtToken().getPrefix() + " ", "");

        UserSession userSession = new UserSession();
        if (StringUtils.isNotBlank(jwtToken)) {
            String userSessionString = CHERRY.SPRING_CONTEXT.getBean(StringRedisTemplate.class).boundValueOps(CHERRY.REDIS_KEY_SESSION + jwtToken).get();
            userSession = JSON.parseObject(userSessionString, UserSession.class);
        }

        return userSession;
    }
}
