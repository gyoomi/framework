/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.service;

import com.cherry.framework.core.BaseService;
import com.cherry.framework.core.model.BusinessException;
import com.cherry.framework.platform.aspect.Log;
import com.cherry.framework.platform.constant.JWTConstant;
import com.cherry.framework.platform.jwt.JwtTokenUtil;
import com.cherry.framework.platform.jwt.JwtUser;
import com.cherry.framework.platform.model.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/15 15:03
 */
@Service
public class UserService extends BaseService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTConstant jwtConstant;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * 根据用户查询用户
     *
     * @param loginName
     * @return
     * @throws Exception
     */
    @Log(content = "根据用户查询用户")
    public UserEntity findUserByLoginName(String loginName) throws Exception {
        String sql = "SELECT * FROM fr_user WHERE login_name = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(UserEntity.class), loginName);
    }


    /**
     * 保存用户
     *
     * @param userEntity
     * @return
     * @throws Exception
     */
    @Log(content = "保存用户")
    public UserEntity save(UserEntity userEntity) throws Exception {
        if (isFieldDuplicate("fr_user", "login_name", userEntity.getLoginName(), null)) {
            throw new BusinessException("用户已存在");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        Long id = insertIntoTable(userEntity);
        userEntity.setId(id);
        return userEntity;
    }

    /**
     * 登录校验
     *
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    @Log(content = "登录校验用户")
    public String login(String loginName, String password) throws Exception {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            throw new BusinessException("用户名或密码不能为空");
        }
        UserEntity userEntity = this.findUserByLoginName(loginName);
        if (null == userEntity) {
            throw new BusinessException("用户不存在，请联系管理员");
        }
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BusinessException("密码错误");
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginName, password);
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserEntity(userEntity);
        String token = jwtTokenUtil.createToken(jwtUser);
        return token;
    }

    /**
     * 刷新口令
     *
     * @param oldToken
     * @return
     * @throws BusinessException
     */
    @Log(content = "刷新令牌")
    public String refresh(String oldToken) throws Exception {
        final String token = oldToken.substring((jwtConstant.JWT_TOKENHEAD + " ").length());
        String loginName = jwtTokenUtil.getLoginNameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(loginName);
        if (jwtTokenUtil.validatToken(token, jwtUser)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
