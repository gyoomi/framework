/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.config;

import com.cherry.framework.platform.constant.CommonConstant;
import com.cherry.framework.platform.constant.JWTConstant;
import com.cherry.framework.platform.filter.JwtAuthenticationTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/15 21:21
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguratioin extends WebSecurityConfigurerAdapter {

    private static final Logger lg = LoggerFactory.getLogger(WebSecurityConfiguratioin.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CommonConstant commonConstant;

    @Autowired
    JWTConstant jwtConstant;

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        lg.info("----------Configure Spring Security Start----------");
        String serverPath = commonConstant.SERVER_SERVLET_PATH;
        if ("/".equals(serverPath)) {
            serverPath = "";
        }
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(serverPath + jwtConstant.JWT_ROUTE_AUTHENTICATION_PATH + "/**").permitAll()
                .antMatchers(serverPath + jwtConstant.JWT_ROUTE_REGISTER + "/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                /* swagger start */
                .antMatchers(serverPath + "/swagger-ui.html").permitAll()
                .antMatchers(serverPath + "/swagger-resources/**").permitAll()
                .antMatchers(serverPath + "/images/**").permitAll()
                .antMatchers(serverPath + "/webjars/**").permitAll()
                .antMatchers(serverPath + "/v2/api-docs").permitAll()
                .antMatchers(serverPath + "/configuration/ui").permitAll()
                .antMatchers(serverPath + "/configuration/security").permitAll()
                /* swagger end */
                .antMatchers(HttpMethod.GET, new String[]{"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js"}).permitAll()
                .anyRequest().permitAll();

        /* httpSecurity.headers().cacheControl(); */
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        lg.info("----------Configure Spring Security End----------");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
