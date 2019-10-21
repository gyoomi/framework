package com.gyoomi.adam.core.jwt;

import com.gyoomi.adam.core.properties.AdamProperties;
import com.gyoomi.adam.core.utils.DateUtils;
import com.gyoomi.adam.rbac.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtTokenUtils
 *
 * @author Leon
 * @version 2019/10/20 21:15
 */
@Component
public class JwtTokenUtils {

    private static final Logger lg = LoggerFactory.getLogger(JwtTokenUtils.class);

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
        return jwtPrefix + " " + jwtToken;
    }

    /**
     * 根据用户信息生成口令
     * <p>注意： 不能嵌套放置，否则无法解析</p>
     *
     * @param jwtUser jwtUser
     * @return token
     */
    public static String createToken(JwtUser jwtUser) {
        Map<String, Object> claims = new HashMap<>();
        User user = jwtUser.getUser();
        claims.put("loginName", user.getLoginName());
        claims.put("nickName", user.getNickName());
        claims.put("headImg", user.getHeadImg());
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("email", user.getEmail());
        claims.put("createDate", new Date());
        return createToken(claims);
    }

    /**
     * 根据token得到数据声明
     *
     * @param token token
     * @return result
     */
    public static Map<String, Object> getClaimsFromToken(String token) throws ExpiredJwtException {
        String realToken = token.replace(adamProperties.getSecurity().getJwtToken().getPrefix() + " ", "");

        return Jwts.parser()
                    .setSigningKey(adamProperties.getSecurity().getJwtToken().getSecret())
                    .parseClaimsJws(realToken)
                    .getBody();
    }

    /**
     * 解析用户
     *
     * @param token token
     * @return result
     */
    public static String getLoginNameFromToken(String token) {
        String loginName;
        try {
            loginName = ((String) getClaimsFromToken(token).get("loginName"));
        } catch (Exception e) {
            loginName = null;
            lg.error("JWT Error :", e);
        }
        return loginName;
    }

    /**
     * 判断口令是否过期
     *
     * @param token
     * @return result
     */
    public static boolean isExpired(String token) {
        boolean isExpired = false;
        try {
            Map<String, Object> claims = JwtTokenUtils.getClaimsFromToken(token);
        } catch (Exception e) {
            isExpired = true;
            lg.error("JWT expired error :", e);
        }
        return isExpired;
    }

//    /**
//     * 刷新口令
//     *
//     * @param token
//     * @return
//     * @throws Exception
//     */
//    public static String refreshToken(String token) {
//        String reToken = null;
//        try {
//            Claims claims = getClaimsFromToken(token);
//            claims.put("created", new Date());
//            reToken = createToken(claims);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return reToken;
//    }

    /**
     * 验证口令
     *
     * @param token token
     * @param userDetails userDetails
     * @return result
     */
    public static boolean valid(String token, UserDetails userDetails) {
        if (null == token || null == userDetails) {
            return false;
        }

        JwtUser jwtUser = (JwtUser) userDetails;
        String loginName = getLoginNameFromToken(token);
        // failure
        if (loginName == null || jwtUser == null) {
            return false;
        }
        return loginName.equals(jwtUser.getUser().getLoginName()) && !isExpired(token);
    }

    @Autowired
    public void setProjectProperties(AdamProperties adamProperties) {
        JwtTokenUtils.adamProperties = adamProperties;
    }

}
