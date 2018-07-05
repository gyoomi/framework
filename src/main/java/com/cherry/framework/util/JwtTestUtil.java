/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/5 16:50
 */
public class JwtTestUtil {

    public static String createToken() throws Exception {
        Date expireDate = new Date(System.currentTimeMillis() + 600000);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", "admin");
        claims.put("date", new Date());
        String jwt = Jwts.builder().setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
        return jwt;
    }


    public static Claims getClaimsFromToken(String token) throws Exception {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
            e.printStackTrace();
        }
        return claims;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(createToken());
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJkYXRlIjoxNTMwNzgyMjc0Nzk0LCJleHAiOjE1MzA3ODI4NzQsInVzZXIiOiJhZG1pbiJ9.KWK9FJF8V6t3jx5BUr9oyOUOhRiNglHIp71IHSaCS4I";
        Claims claims = getClaimsFromToken(token);
        System.out.println(claims);
    }

}
