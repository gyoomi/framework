/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.core.filter;

import com.gyoomi.adam.core.BaseController;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.properties.AdamProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * token有效期拦截（模拟传统的Session,弥补JWT的不足）
 *
 * @author Leon
 * @date 2019-10-22 16:53
 */
public class TokenSessionFilter extends OncePerRequestFilter {

    private static final Logger lg = LoggerFactory.getLogger(TokenSessionFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        if (isExclusives(req) || !hasExpired(req)) {
            filterChain.doFilter(req, resp);
        } else {
            lg.warn("token参数已超时，请重新登录！");
            Response responseResult = Response.builder().code(HttpServletResponse.SC_NOT_IMPLEMENTED).msg("token参数已超时，请重新登录！").build();
            BaseController.writeJsonResponse(responseResult, resp);
        }
    }

    private boolean isExclusives(HttpServletRequest request) {
        List<String> exclusivePath = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class).getSecurity().getExclusivePath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestURI = request.getRequestURI();
        for (String exclusive : exclusivePath) {
            if (antPathMatcher.match(exclusive, requestURI)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasExpired(HttpServletRequest req) {
        AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        String jwtToken = req.getHeader(adamProperties.getSecurity().getJwtToken().getHeaderName());
        if (StringUtils.isBlank(jwtToken)) {
            jwtToken = req.getParameter(adamProperties.getSecurity().getJwtToken().getRequestName());
        }
        jwtToken = jwtToken.replace(adamProperties.getSecurity().getJwtToken().getPrefix() + " ", "");

        boolean existed = CHERRY.SPRING_CONTEXT.getBean(StringRedisTemplate.class).hasKey(CHERRY.REDIS_KEY_SESSION + jwtToken);
        return !existed;
    }
}
