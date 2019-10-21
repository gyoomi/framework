package com.gyoomi.adam.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyoomi.adam.rbac.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/21 21:19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据登录名查询用户
     *
     * @param loginName 登录名
     * @return 用户
     */
    @Select(" SELECT * FROM adam_user WHERE loginName = #{loginName} ")
    User findUserByLoginName(String loginName);
}
