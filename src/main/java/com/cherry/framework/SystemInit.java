/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework;

import com.cherry.framework.thread.BaseThread;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/8/23 14:40
 */
@Configuration
public class SystemInit implements InitializingBean, DisposableBean {

    @Autowired
    private Map<String, BaseThread> threads = new HashMap<>();

    @Override
    public void destroy() {
        threads.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
            System.out.println("--------------------");
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init start.....................................................");
        System.out.println(threads);
        System.out.println("init end  .....................................................");
    }
}
