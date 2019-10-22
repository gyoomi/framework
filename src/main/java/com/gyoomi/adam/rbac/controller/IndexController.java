package com.gyoomi.adam.rbac.controller;

import com.gyoomi.adam.core.BaseController;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.jwt.JwtTokenUtils;
import com.gyoomi.adam.core.jwt.JwtUser;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.core.properties.AdamProperties;
import com.gyoomi.adam.rbac.model.User;
import com.gyoomi.adam.rbac.model.request.LoginVO;
import com.gyoomi.adam.rbac.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/21 22:55
 */
@RestController
public class IndexController extends BaseController {

    private static final Logger lg = LoggerFactory.getLogger(IndexController.class);


    /**
     * 注册用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/api/register")
    public Response register(User user) throws Exception {
        User registerUser = CHERRY.SPRING_CONTEXT.getBean(UserService.class).register(user);
        registerUser.setPassword("");

        return Response.builder()
                .code(HttpServletResponse.SC_OK)
                .msg("ok")
                .data(registerUser)
                .build();

    }

    /**
     * 登录
     *
     * @param loginVO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/api/login")
    public Response login(LoginVO loginVO) throws Exception {
        AdamProperties adamProperties = CHERRY.SPRING_CONTEXT.getBean(AdamProperties.class);
        User user = CHERRY.SPRING_CONTEXT.getBean(UserService.class).login(loginVO);
        if (null != user) {
            HashMap<String, Object> data = new HashMap<>(16);

            JwtUser jwtUser = new JwtUser();
            jwtUser.setUser(user);
            String csrf = UUID.randomUUID().toString().replace("-", "");
            data.put("token", JwtTokenUtils.createToken(jwtUser));
            data.put("csrf", csrf);
            data.put("createDate", LocalDateTime.now().format(CHERRY.FORMATTER_DATE_TIME));

//            AbstractAuthenticationToken token = new UsernamePasswordAuthenticationToken(jwtUser, null, null);
//            SecurityContextHolder.getContext().setAuthentication(token);
            CHERRY.SPRING_CONTEXT.getBean(StringRedisTemplate.class).boundValueOps(CHERRY.REDIS_KEY_CSRF + csrf).set("", adamProperties.getSecurity().getSignIn().getExpiration(), TimeUnit.SECONDS);


            return Response.builder()
                    .code(HttpServletResponse.SC_OK)
                    .data(data)
                    .build();
        } else {
            // 登陆失败
            lg.warn("“{}”尝试登录系统失败！", loginVO.getLoginName());

            return Response.builder()
                    .code(HttpServletResponse.SC_FORBIDDEN)
                    .msg("登录失败：无效的账号信息，请检查您的输入！")
                    .build();
        }
    }
}
