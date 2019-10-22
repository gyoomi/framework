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

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Csrf攻防Filter
 *
 * @author Leon
 * @date 2019-10-22 16:10
 */
public class CsrfFilter implements Filter {

    /**
     * Logger
     */
    private final Logger lg = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        if (isExclusives((HttpServletRequest) req) || checkCsrfToken(req)) {
            filterChain.doFilter(req, resp);
        } else {
            lg.warn("无效的CSRF参数：{}", ((HttpServletRequest) req).getRequestURI());
            Response response = Response.builder().code(HttpServletResponse.SC_SERVICE_UNAVAILABLE).msg("无效的CSRF参数").build();
            BaseController.writeJsonResponse(response, (HttpServletResponse) resp);
        }
    }

    /**
     * 检查CSRF Token
     *
     * @param req   ServletRequest
     */
    private boolean checkCsrfToken(ServletRequest req) {
        AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        boolean isValid = false;

        String csrf = ((HttpServletRequest) req).getHeader(adamProperties.getCsrf().getHeaderName());
        if (StringUtils.isBlank(csrf)) {
            csrf = req.getParameter(adamProperties.getCsrf().getRequestName());
        }

        if (StringUtils.isNotBlank(csrf)) {
            Boolean hasKey = CHERRY.SPRING_CONTEXT.getBean(StringRedisTemplate.class).hasKey(CHERRY.REDIS_KEY_CSRF + csrf);
            isValid = hasKey == null ? false : hasKey;
        }

        return isValid;
    }

    /**
     * 过滤非认证URL
     * <p>和Spring Security的白名单类似</p>
     *
     * @param request req
     * @return 返回结果
     */
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
}
