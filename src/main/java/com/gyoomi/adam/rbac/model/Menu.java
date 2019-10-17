package com.gyoomi.adam.rbac.model;

import com.gyoomi.adam.rbac.enums.MenuType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:37
 */
@Accessors(chain = true)
@Data
public class Menu implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单编号：顶级编号为root
     */
    private String nodeNo;

    /**
     * 父菜单编号
     */
    private String parentNodeNo;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * URL
     */
    private String url;

    /**
     * 类型：
     * 目录，菜单，按钮
     *
     * @see MenuType
     */
    private String menuType;

    /**
     * 菜单状态：
     * 0隐藏,1显示
     */
    private Integer visible;

    /**
     * 权限唯一标识字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 创建人id
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人id
     */
    private Long updateUser;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remark;
}