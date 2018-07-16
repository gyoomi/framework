/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.controller;

import com.cherry.framework.jwt.JwtUser;
import com.cherry.framework.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 *  基类    Controller
 *
 * @author Leon
 * @version 2018/6/14 17:22
 */
public abstract class BaseController {

    private static final Logger lg = LoggerFactory.getLogger(BaseController.class);

    protected UserEntity getCurrentUser(HttpServletRequest req) {
        return ((JwtUser) req.getAttribute("currentUser")).getUserEntity();
    }
}
