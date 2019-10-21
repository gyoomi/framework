package com.gyoomi.adam.rbac.controller;

import com.gyoomi.adam.core.BaseController;
import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.core.model.Response;
import com.gyoomi.adam.rbac.model.User;
import com.gyoomi.adam.rbac.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/21 22:55
 */
@RestController
public class IndexController extends BaseController {

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
}
