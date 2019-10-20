package com.gyoomi.adam.core.jwt;

import com.gyoomi.adam.core.properties.AdamProperties;
import com.gyoomi.adam.core.utils.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/20 21:15
 */
@Component
public class JwtTokenUtils {

    @Autowired
    private static AdamProperties adamProperties;


    /**
     * 生成token
     *
     * @param claim claim
     * @return token
     */
    public static String createToken(Map<String, Object> claim) {
        LocalDateTime expireLocalDateTime = LocalDateTime.now().plus(adamProperties.getSecurity().getSignIn().getExpiration(), ChronoUnit.SECONDS);

        String jwtPrefix = adamProperties.getSecurity().getJwtToken().getPrefix() + " ";
        String jwtToken = Jwts.builder()
                .setClaims(claim)
                .setExpiration(DateUtils.localDateTimeToDate(expireLocalDateTime))
                .signWith(SignatureAlgorithm.HS512, adamProperties.getSecurity().getJwtToken().getSecret())
                .compact();
        return jwtPrefix + jwtToken;
    }

    /**
     * 根据用户，生成口令
     *
     * @param jwtUser jwtUser
     * @return token
     */
//    public String createToken(JwtUser jwtUser) throws Exception {
//        Map<String, String> claims = BeanUtils.describe(jwtUser.getUser());
//        claims.put("created", new Date());
//        return createToken(claims);
//    }
    public static void main(String[] args) {
//        new User()
//
//        BeanUtils.describe();
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
            claims = Jwts.parser().setSigningKey(adamProperties.getSecurity().getJwtToken().getSecret()).parseClaimsJws(token).getBody();
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
    public boolean validateToken(String token, UserDetails userDetails) {
        JwtUser jwtUser = (JwtUser) userDetails;
        String loginName = getLoginNameFromToken(token);
        return loginName.equals(jwtUser.getUsername()) && !isExpired(token);
    }

    @Autowired
    public void setProjectProperties(AdamProperties adamProperties) {
        JwtTokenUtils.adamProperties = adamProperties;
    }

}
