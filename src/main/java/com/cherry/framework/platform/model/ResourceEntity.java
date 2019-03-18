package com.cherry.framework.platform.model;

import com.cherry.framework.core.rowmapper.DBColumn;
import com.cherry.framework.core.rowmapper.DBTable;

import java.io.Serializable;
import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/11 14:37
 */
@DBTable(name = "fr_resource")
public class ResourceEntity implements Serializable {

    /**
     * 主键
     */
    @DBColumn(isPrimaryKey = true, name = "id")
    private Integer id;

    /**
     * URL
     */
    @DBColumn(name = "url")
    private String url;

    /**
     * 名字
     */
    @DBColumn(name = "name")
    private String name;

    /**
     * 创建人id
     */
    @DBColumn(name = "create_user")
    private Integer createUser;

    /**
     * 创建人
     */
    @DBColumn(name = "create_user_name")
    private String createUserName;

    /**
     * 创建时间
     */
    @DBColumn(name = "create_date")
    private Date createDate;

    /**
     * 修改人id
     */
    @DBColumn(name = "update_user")
    private Integer updateUser;

    /**
     * 修改人
     */
    @DBColumn(name = "update_user_name")
    private String updateUserName;

    /**
     * 修改时间
     */
    @DBColumn(name = "update_date")
    private Date updateDate;

    /**
     * 备注
     */
    @DBColumn(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", createUser=" + createUser +
                ", createUserName='" + createUserName + '\'' +
                ", createDate=" + createDate +
                ", updateUser=" + updateUser +
                ", updateUserName='" + updateUserName + '\'' +
                ", updateDate=" + updateDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}