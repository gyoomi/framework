/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT  Constant
 *
 * @author Leon
 * @version 2018/7/2 22:59
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConstant {

    /**
     * 头
     */
    public String JWT_HEADERS;

    /**
     * 秘钥
     */
    public String JWT_SECERT;

    /**
     * 过期时间(分钟)
     */
    public Long JWT_EXPIRETIME;

    /**
     * token头
     */
    public String JWT_TOKENHEAD;

    /**
     * 认证路径
     */
    public String JWT_ROUTE_AUTHENTICATION_PATH;

    /**
     * 刷新
     */
    public String JWT_ROUTE_REFRESHTOKEN_PATH;

    /**
     * 注册
     */
    public String JWT_ROUTE_REGISTER;


    public String getJWT_HEADERS() {
        return JWT_HEADERS;
    }

    public void setJWT_HEADERS(String JWT_HEADERS) {
        this.JWT_HEADERS = JWT_HEADERS;
    }

    public String getJWT_SECERT() {
        return JWT_SECERT;
    }

    public void setJWT_SECERT(String JWT_SECERT) {
        this.JWT_SECERT = JWT_SECERT;
    }

    public Long getJWT_EXPIRETIME() {
        return JWT_EXPIRETIME;
    }

    public void setJWT_EXPIRETIME(Long JWT_EXPIRETIME) {
        this.JWT_EXPIRETIME = JWT_EXPIRETIME;
    }

    public String getJWT_TOKENHEAD() {
        return JWT_TOKENHEAD;
    }

    public void setJWT_TOKENHEAD(String JWT_TOKENHEAD) {
        this.JWT_TOKENHEAD = JWT_TOKENHEAD;
    }

    public String getJWT_ROUTE_AUTHENTICATION_PATH() {
        return JWT_ROUTE_AUTHENTICATION_PATH;
    }

    public void setJWT_ROUTE_AUTHENTICATION_PATH(String JWT_ROUTE_AUTHENTICATION_PATH) {
        this.JWT_ROUTE_AUTHENTICATION_PATH = JWT_ROUTE_AUTHENTICATION_PATH;
    }

    public String getJWT_ROUTE_REFRESHTOKEN_PATH() {
        return JWT_ROUTE_REFRESHTOKEN_PATH;
    }

    public void setJWT_ROUTE_REFRESHTOKEN_PATH(String JWT_ROUTE_REFRESHTOKEN_PATH) {
        this.JWT_ROUTE_REFRESHTOKEN_PATH = JWT_ROUTE_REFRESHTOKEN_PATH;
    }

    public String getJWT_ROUTE_REGISTER() {
        return JWT_ROUTE_REGISTER;
    }

    public void setJWT_ROUTE_REGISTER(String JWT_ROUTE_REGISTER) {
        this.JWT_ROUTE_REGISTER = JWT_ROUTE_REGISTER;
    }
}
