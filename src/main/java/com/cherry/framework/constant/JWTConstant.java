/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.constant;

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
}
