package com.gyoomi.adam.rbac.service;

import com.gyoomi.adam.core.BaseService;
import com.gyoomi.adam.core.BusinessException;
import com.gyoomi.adam.rbac.dao.UserMapper;
import com.gyoomi.adam.rbac.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/21 22:57
 */
@Service
public class UserService extends BaseService {

    /**
     * 注册用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    public User register(User user) throws Exception {
        if (StringUtils.isBlank(user.getLoginName())) {
            throw new BusinessException("登录名不能为空！");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new BusinessException("密码不能为空！");
        }
        if (user.getPassword().trim().length() < 6) {
            throw new BusinessException("密码长度不能小于6位！");
        }
        user.setCreateDate(new Date());
        user.setStatus(1);
        user.setPassword(getSpringBean(PasswordEncoder.class).encode(user.getPassword()));
        getSpringBean(UserMapper.class).insert(user);
        return user;
    }
}
