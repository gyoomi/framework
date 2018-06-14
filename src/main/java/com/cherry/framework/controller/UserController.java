/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.controller;

import com.cherry.framework.model.UserEntity;
import com.cherry.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/6/14 17:21
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    /**
     * 新建或编辑用户
     *
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/user/save")
    public int save(UserEntity userEntity) {
        int rows = userService.save(userEntity);
        return rows;
    }

    /**
     * 列表查询
     *
     * @return
     */
    @RequestMapping(value = "/user/list")
    public List<UserEntity> findUserList(int pageNum, int pageSize) {
        List<UserEntity> list = userService.findAllUserList(pageNum, pageSize);
        return list;
    }
}
