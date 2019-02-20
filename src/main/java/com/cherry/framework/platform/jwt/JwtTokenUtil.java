/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.jwt;

import com.cherry.framework.platform.constant.JWTConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
    public String createToken(Map<String, Object> claim) {
        // Sign date
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, jwtConstant.JWT_EXPIRETIME.intValue());
        // Expire date
        Date expireDate = nowTime.getTime();

        String jwt = jwtConstant.JWT_TOKENHEAD + " " +
                Jwts.builder()
                .setClaims(claim)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtConstant.JWT_SECERT)
                .compact();
        return jwt;
    }

    /**
     * 根据用户模型，生成口令
     *
     * @param userDetails
     * @return
     */
    public String createToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        return createToken(claims);
    }

    /**
     * 根据token得到数据声明
     *
     * @param token
     * @return
     * @throws Exception
     */
    public Claims getClaimsFromToken(String token) {
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
     * 从口令中获得用户名
     *
     * @param token
     * @return
     * @throws Exception
     */
    public String getLoginNameFromToken(String token){
        String loginName = null;
        try {
            Claims claims = getClaimsFromToken(token);
            loginName = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginName;
    }

    /**
     * 判断口令是否过期
     *
     * @param token
     * @return
     * @throws Exception
     */
    public boolean isExpired(String token) {
        boolean isExpired = true;
        try {
            isExpired = getClaimsFromToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExpired;
    }

    /**
     * 刷新口令
     *
     * @param token
     * @return
     * @throws Exception
     */
    public String refreshToken(String token) {
        String reToken = null;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            reToken = createToken(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reToken;
    }

    /**
     * 验证口令
     *
     * @param token
     * @param userDetails
     * @return
     * @throws Exception
     */
    public boolean validatToken(String token, UserDetails userDetails) {
        JwtUser jwtUser = (JwtUser) userDetails;
        String loginName = getLoginNameFromToken(token);
        return loginName.equals(jwtUser.getUsername()) && !isExpired(token);
    }
}
