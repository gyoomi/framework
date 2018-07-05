/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.util;

import com.cherry.framework.constant.JWTConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/5 14:04
 */
@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
    JWTConstant jwtConstant;

    public String createToken(Map<String, Object> claim) throws Exception {
        // Sign date
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, jwtConstant.JWT_EXPIRETIME.intValue());
        // Expire date
        Date expireDate = nowTime.getTime();

        String jwt = Jwts.builder()
                .setClaims(null)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtConstant.JWT_SECERT)
                .compact();
        return jwt;
    }

}
