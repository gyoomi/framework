/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.rbac.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-22 9:13
 */
@Data
public class LoginVO implements Serializable {

    /**
     * loginName
     */
    private String loginName;

    /**
     * password
     */
    private String password;
}
