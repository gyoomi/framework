package com.cherry.framework.platform.service;

import com.cherry.framework.platform.jwt.JwtAuthority;
import com.cherry.framework.platform.jwt.JwtUser;
import com.cherry.framework.platform.model.ResourceEntity;
import com.cherry.framework.platform.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Jwt 服务支持类
 *
 * @author Leon
 * @version 2018/7/15 17:53
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        String sql = "SELECT * FROM fr_user WHERE login_name = ?";
        UserEntity userEntity = jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(UserEntity.class), loginName);
        if (null == userEntity) {
            throw new RuntimeException("用户不存在");
        }
        String resourceSql = " SELECT "
                           + "     t1.* "
                           + " FROM "
                           + " fr_resource t1 "
                           + " INNER JOIN fr_role_resource t2 ON t2.resource_id = t1.id "
                           + " INNER JOIN fr_user_role t3 on t3.role_id = t2.role_id "
                           + " WHERE t3.user_id = ? ";
        List<ResourceEntity> resourceList = jdbc.query(resourceSql, new BeanPropertyRowMapper<>(ResourceEntity.class), userEntity.getId());
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserEntity(userEntity);
        List<JwtAuthority> authorities = new ArrayList<>();
        Optional<List<ResourceEntity>> resourceListOpt = Optional.ofNullable(resourceList);
        if (resourceListOpt.isPresent()) {
            resourceListOpt.get().stream().map(r -> {return new JwtAuthority(r);}).forEach(authorities::add);
        }
        jwtUser.setAuthorities(authorities);
        return jwtUser;
    }
}
