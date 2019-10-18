/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-18 15:33
 */
@Data
@TableName(value = "user")
public class User {

    private String id;

    private String loginName;

    private String nickName;

    private String password;

    private String remark;
}
