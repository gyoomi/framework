/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework;

import com.cherry.framework.core.CHERRY;
import com.cherry.framework.core.thread.BaseThread;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.oro.text.regex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.List;


/**
 * 系统配置基类
 *
 * @author Leon
 * @version 2018/8/23 14:40
 */
@WebServlet(loadOnStartup = 2)
public class SystemInit extends HttpServlet {

    private static final Logger lg = LoggerFactory.getLogger(SystemInit.class);

    private Configuration systemConfigXML;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public SystemInit(){}

    @Override
    public void init() throws ServletException {
        lg.info("Initialize system operating parameters......");
        try {
            // loadLicense
            // Spring Context
            CHERRY.SPRING_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            // Web应用发布路径
            CHERRY.WEB_APP_PATH = getServletContext().getRealPath("/");
            if (CHERRY.WEB_APP_PATH.endsWith("\\") || CHERRY.WEB_APP_PATH.endsWith("/")) {
                CHERRY.WEB_APP_PATH = CHERRY.WEB_APP_PATH.substring(0, CHERRY.WEB_APP_PATH.length() - 1);
            }
            lg.info("Web应用磁盘路径：{}", CHERRY.WEB_APP_PATH);
            // Init XML
            Configurations cfg = new Configurations();
            systemConfigXML = cfg.xml(new ClassPathResource("/system-config.xml").getFile());
            initXMLParameter();
            initThread();
            // TODO
        } catch (Exception e) {
            lg.error("System initialization error：", e);
        }
    }

    private void initXMLParameter() throws Exception {
        // 1 system-config.xml - 系统参数
        CHERRY.STATUS = systemConfigXML.getString("status").trim();
        lg.info("运行状态：{}", CHERRY.STATUS);
        CHERRY.ATTACHMENT_ENGINE = systemConfigXML.getString("attachment-engine").trim();
        lg.info("附件存储引擎：{}", CHERRY.ATTACHMENT_ENGINE);
        // 登陆安全
        CHERRY.LOGIN_LOCK = systemConfigXML.getString("login.lock").trim().equalsIgnoreCase("true");
        lg.info("登录安全检查开启标识：{}", CHERRY.LOGIN_LOCK);
        CHERRY.LOGIN_MAX_ERROR_TIMES = systemConfigXML.getInt("login.times");
        lg.info("登录失败最大重试次数：{}", CHERRY.LOGIN_MAX_ERROR_TIMES);
        CHERRY.LOGIN_LOCK_DURATION = systemConfigXML.getInt("login.duration");
        lg.info("登录失败锁定时长：{}", CHERRY.LOGIN_LOCK_DURATION);
        CHERRY.LOGIN_CODE = systemConfigXML.getString("login.code").trim().equalsIgnoreCase("true");
        lg.info("登录页验证码功能开启标志：{}", CHERRY.LOGIN_CODE);

        // 2 数据库配置
        Pattern pt = null;
        String url = this.url;
        if (url.contains("mysql")) {
            // jdbc:mysql://192.168.1.100:3306/NewStarEdu?zeroDateTimeBehavior=convertToNull
            CHERRY.DB_TYPE = "mysql";
            pt = new Perl5Compiler().compile("jdbc:mysql://([^:]+):(\\d+)/([^\\?]+)");
        } else if (url.contains("oracle")) {
            // jdbc:oracle:thin:@192.168.100.151:1521:FORP
            CHERRY.DB_TYPE = "oracle";
            pt = new Perl5Compiler().compile("jdbc:oracle:thin:@([^:]+):(\\d+):(\\w+)");
        } else {
            CHERRY.DB_TYPE = "unknown";
        }

        PatternMatcher pm = new Perl5Matcher();
        if (pm.contains(url, pt)) {
            MatchResult mr = pm.getMatch();
            CHERRY.DB_IP = mr.group(1);
            CHERRY.DB_PORT = mr.group(2);
            CHERRY.DB_NAME = mr.group(3);
        }

        CHERRY.DB_USER_NAME = this.dbUserName;
        CHERRY.DB_PASSWORD = this.dbPassword;
        lg.info("数据库：{}[{}]", CHERRY.DB_NAME, CHERRY.DB_TYPE);
        lg.debug("{}:{}/{}", CHERRY.DB_IP, CHERRY.DB_PORT, CHERRY.DB_USER_NAME);

        lg.info("初始化XML配置参数[OK]");
    }

    private void initThread() throws Exception {
        List<Object> threads = systemConfigXML.getList("threads.thread");

        if (null != threads && threads.size() > 0) {
            BaseThread service;
            String clazz, name, start;
            for (int i = 0; i < threads.size(); i++) {
                try {
                    clazz = (String) threads.get(i);
                    name = systemConfigXML.getString("threads.thread(" + i + ")[@name]");
                    start = systemConfigXML.getString("threads.thread(" + i + ")[@start]");

                    service = (BaseThread)(Class.forName(clazz.trim()).getConstructor(new Class[]{String.class}).newInstance(name));
                    if ("true".equalsIgnoreCase(start.trim())) {
                        service.start();
                    }

                    CHERRY.THREADS.put(clazz, service);
                } catch (Exception e) {
                    lg.error("Thread init failed：", e);
                }
            }
        }

        lg.info("初始化后台Threads参数[OK]");
    }

    @Override
    public void destroy() {
        try {
            // 服务
            for (String s : CHERRY.THREADS.keySet()) {
                CHERRY.THREADS.get(s).stopService();
            }

            // JDBC Drivers
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            Driver d;
            while (drivers.hasMoreElements()) {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                lg.info("释放JDBC驱动：{}", d);
            }
        }  catch (Exception e) {
            lg.error("系统退出错误：", e);
        }
    }
}
