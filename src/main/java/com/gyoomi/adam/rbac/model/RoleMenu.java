package com.gyoomi.adam.rbac.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/4/7 17:31
 */
@Accessors(chain = true)
@Data
public class RoleMenu implements Serializable {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
