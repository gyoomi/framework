/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.core.filter;

import com.gyoomi.adam.core.BaseController;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.model.UserSession;
import com.gyoomi.adam.core.properties.AdamProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-22 16:52
 */
public class PermissionFilter extends OncePerRequestFilter {

    private static final Logger lg = LoggerFactory.getLogger(PermissionFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        if (isExclusives(req) || hasPermission(req)) {
            filterChain.doFilter(req, resp);
        } else {
            lg.warn("权限不足，请确认后重试！");
            Response responseResult = Response.builder().code(HttpServletResponse.SC_FORBIDDEN).msg("权限不足，请确认后重试！").build();
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

    private boolean hasPermission(HttpServletRequest request) {
        boolean hasPermission = false;

        UserSession userSession = UserSession.getUserSession();
        Set<String> urls = userSession.getUrls();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String requestURI = request.getRequestURI();
        for (String url : urls) {
            if (pathMatcher.match(url, requestURI)) {
                hasPermission = true;
                break;
            }
        }
        return hasPermission;
    }
}
