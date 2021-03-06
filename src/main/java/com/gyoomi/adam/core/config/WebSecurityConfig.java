package com.gyoomi.adam.core.config;

import com.gyoomi.adam.core.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig
 *
 * @author Leon
 * @version 2019/10/21 20:50
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new InterfaceAccessKeyFilter(), ChannelProcessingFilter.class)
                .addFilterAfter(new CsrfFilter(), InterfaceAccessKeyFilter.class)
                .addFilterBefore(new TokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new TokenSessionFilter(), TokenFilter.class)
                .addFilterAfter(new PermissionFilter(), TokenSessionFilter.class)
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                    .authorizeRequests().antMatchers("/api/register", "/interface/**", "/api/login", "/api/logout").permitAll()
                    .anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
