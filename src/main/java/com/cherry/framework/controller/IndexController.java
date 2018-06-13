/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/6/13 21:53
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        return "boot-mybatis";
    }
}
