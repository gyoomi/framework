package com.gyoomi.adam.rbac.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/4/7 17:08
 */
@Accessors(chain = true)
@Data
@TableName(value = "adam_user_role")
public class UserRole implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;
}
