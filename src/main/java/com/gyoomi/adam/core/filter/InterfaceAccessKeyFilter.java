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
import com.gyoomi.adam.rbac.model.InterfaceClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-22 16:17
 */
public class InterfaceAccessKeyFilter implements Filter {

    /**
     * Logger
     */
    private final Logger lg = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        if (isExclusives((HttpServletRequest) req) || checkAccessKey(req)) {
            // 校验通过
            filterChain.doFilter(req, resp);
        } else {
            // 校验失败
            lg.warn("无效的AK参数：{}", ((HttpServletRequest) req).getRequestURI());
            Response response = Response.builder().code(HttpServletResponse.SC_NOT_IMPLEMENTED).msg("无效的AK参数").build();
            BaseController.writeJsonResponse(response, (HttpServletResponse) resp);
        }
    }

    /**
     * 检查Access Key
     *
     * @param req   ServletRequest
     */
    private boolean checkAccessKey(ServletRequest req) {
        AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        boolean isValid = false;

        String ak = ((HttpServletRequest) req).getHeader(adamProperties.getAccessKey().getHeaderName());
        if (StringUtils.isBlank(ak)) {
            ak = req.getParameter(adamProperties.getAccessKey().getRequestName());
        }

        if (StringUtils.isNotBlank(ak)) {
            // 检查AK有效性
            if (CHERRY.CACHE_INTERFACE.containsKey(ak)) {
                InterfaceClient ic = CHERRY.CACHE_INTERFACE.get(ak);
                // 设备存在并且有效 -> 放行; 无效 -> 拦截
                if (null != ic && 1 == ic.getStatus()) {
                    isValid = true;
                }
            }
        }

        return isValid;
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
}
