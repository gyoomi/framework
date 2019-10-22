/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.rbac.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-22 15:32
 */
@Data
@TableName(value = "adam_interface_client")
public class InterfaceClient implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 客户端说明
     */
    private String client;

    /**
     * ak
     */
    private String ak;

    /**
     * 状态： 1 - 可用； 2 - 禁用
     */
    private Integer status;
}
