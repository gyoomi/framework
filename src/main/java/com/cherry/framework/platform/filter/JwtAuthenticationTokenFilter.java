/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.filter;

import com.cherry.framework.platform.constant.JWTConstant;
import com.cherry.framework.platform.jwt.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token过滤器
 *
 * @author Leon
 * @version 2018/7/15 22:46
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger lg = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JWTConstant jwtConstant;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtConstant.JWT_HEADERS);
        String tokenHeader = jwtConstant.JWT_TOKENHEAD + " ";
        if (authHeader != null && authHeader.startsWith(tokenHeader)) {
            final String authToken = authHeader.substring(tokenHeader.length());
            String loginName = jwtTokenUtil.getLoginNameFromToken(authToken);
            lg.info("Checking authentication -> {}", loginName);
            if (loginName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginName);
                if (jwtTokenUtil.validatToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    request.setAttribute("currentUser", userDetails);
                    lg.info("Checking authentication -> {} successful", loginName);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
