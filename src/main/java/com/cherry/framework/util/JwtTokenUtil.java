/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.util;

import com.cherry.framework.constant.JWTConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @author Leon
 * @version 2018/7/5 14:04
 */
@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
    JWTConstant jwtConstant;

    /**
     * 生成token
     *
     * @param claim
     * @return
     * @throws Exception
     */
    public String createToken(Map<String, Object> claim) throws Exception {
        // Sign date
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, jwtConstant.JWT_EXPIRETIME.intValue());
        // Expire date
        Date expireDate = nowTime.getTime();

        String jwt = Jwts.builder()
                .setClaims(claim)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtConstant.JWT_SECERT)
                .compact();
        return jwt;
    }

    /**
     * 根据token得到数据声明
     *
     * @param token
     * @return
     * @throws Exception
     */
    public Claims getClaimsFromToken(String token) throws Exception {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtConstant.JWT_SECERT).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 判断口令是否过期
     *
     * @param token
     * @return
     * @throws Exception
     */
    public boolean isExpired(String token) throws Exception {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

}
