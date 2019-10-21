package com.gyoomi.adam.core.jwt;

import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.rbac.dao.UserMapper;
import com.gyoomi.adam.rbac.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/21 21:16
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = CHERRY.SPRING_CONTEXT.getBean(UserMapper.class).findUserByLoginName(username);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUser(user);
        return jwtUser;
    }
}
