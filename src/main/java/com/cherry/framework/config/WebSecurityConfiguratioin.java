/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.config;

import com.cherry.framework.constant.CommonConstant;
import com.cherry.framework.constant.JWTConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CommonConstant commonConstant;

    @Autowired
    JWTConstant jwtConstant;

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
        String serverPath = commonConstant.SERVER_SERVLET_PATH;
        if ("/".equals(serverPath)) {
            serverPath = "";
        }
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(serverPath + jwtConstant.JWT_ROUTE_AUTHENTICATION_PATH + "/**").permitAll()
                .antMatchers(serverPath + jwtConstant.JWT_ROUTE_REFRESHTOKEN_PATH + "/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers(HttpMethod.GET, new String[]{"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js"}).permitAll()
                .anyRequest().authenticated();

        httpSecurity.headers().cacheControl();
    }
}
