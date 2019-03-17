package com.cherry.framework.core;

import com.cherry.framework.core.thread.BaseThread;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/17 11:47
 */
public class CHERRY {

    public static final String SESSION_USER = "session_user";
    public static final String MD5_SALT_PREFIX = "Cherry_";
    public static String WEB_APP_CONTEXT = null;
    public static String WEB_APP_PATH = null;
    public static ApplicationContext SPRING_CONTEXT = null;
    public static String STATUS = "dev";
    public static String ATTACHMENT_ENGINE = "file";
    public static Map<String, BaseThread> THREADS = new HashMap<>();
    public static String DB_TYPE = null;
    public static String DB_NAME = null;
    public static String DB_PORT = null;
    public static String DB_IP = null;
    public static String DB_USER_NAME = null;
    public static String DB_PASSWORD = null;
    public static boolean LOGIN_LOCK = false;
    public static int LOGIN_MAX_ERROR_TIMES = 5;
    public static int LOGIN_LOCK_DURATION = 60;
    public static boolean LOGIN_CODE = true;

    public CHERRY() {}
}
