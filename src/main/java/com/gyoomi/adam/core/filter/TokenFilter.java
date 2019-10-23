package com.gyoomi.adam.core.filter;

import com.gyoomi.adam.core.BaseController;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.jwt.JwtTokenUtils;
import com.gyoomi.adam.core.jwt.UserDetailsServiceImpl;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.properties.AdamProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * token拦截器
 *
 * @author Leon
 * @version 2019/10/21 20:58
 */
public class TokenFilter extends OncePerRequestFilter {

    private static final Logger lg = LoggerFactory.getLogger(TokenFilter.class);


    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!isExclusives(request)) {
            AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
            String jwtToken = request.getHeader(adamProperties.getSecurity().getJwtToken().getHeaderName());
            if (StringUtils.isBlank(jwtToken)) {
                jwtToken = request.getParameter(adamProperties.getSecurity().getJwtToken().getRequestName());
            }

            if (StringUtils.isNotBlank(jwtToken) && checkTokenStyle(jwtToken) && checkToken(jwtToken)) {
                filterChain.doFilter(request, response);
            } else {
                lg.warn("token参数为空或失效：{}", jwtToken);
                Response responseResult = Response.builder().code(HttpServletResponse.SC_NOT_IMPLEMENTED).msg("Token参数为空或失效").build();
                BaseController.writeJsonResponse(responseResult, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean checkToken(String token) {
        boolean valid = false;
        String loginNameFromToken = JwtTokenUtils.getLoginNameFromToken(token);
        // TODO
        if (null != loginNameFromToken && null == SecurityContextHolder.getContext().getAuthentication()) {
            UserDetails userDetails = CHERRY.SPRING_CONTEXT.getBean(UserDetailsServiceImpl.class).loadUserByUsername(loginNameFromToken);
            if (JwtTokenUtils.valid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                valid = true;
            }
        }
        return valid;
    }

    /**
     * Check the formatter of token
     *
     * @param token the token needed to be valid
     * @return result
     */
    private boolean checkTokenStyle(String token) {
        AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        String prefix = adamProperties.getSecurity().getJwtToken().getPrefix() + " ";
        return StringUtils.startsWith(token, prefix);
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
