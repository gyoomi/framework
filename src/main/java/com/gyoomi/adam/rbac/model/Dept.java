package com.gyoomi.adam.rbac.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:37
 */
@Data
public class Dept implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编号
     */
    private String nodeNo;

    /**
     * 父部门的编号
     */
    private String parentNodeNo;

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