/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.service.impl;

import com.cherry.framework.platform.dao.ResourceEntityMapper;
import com.cherry.framework.platform.dao.UserEntityMapper;
import com.cherry.framework.platform.jwt.JwtAuthority;
import com.cherry.framework.platform.jwt.JwtUser;
import com.cherry.framework.platform.model.ResourceEntity;
import com.cherry.framework.platform.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserEntityMapper userEntityMapper;

    @Autowired
    ResourceEntityMapper resourceEntityMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityMapper.findUserByLoginName(loginName);
        if (userEntity == null) {
            throw new RuntimeException("用户不存在");
        }
        List<ResourceEntity> resourceList = resourceEntityMapper.findResourceListByUserId(userEntity.getId());
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
