package com.gyoomi.adam.rbac.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/4/7 15:25
 */
@Accessors(chain = true)
@Data
public class LoginLog implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户账号
     */
    private String loginName;

    /**
     * 登录状态 0失败 1成功
     */
    private Integer status;

    /**
     * 登录IP地址
     */
    private String ipAddress;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    private Date loginDate;
}
