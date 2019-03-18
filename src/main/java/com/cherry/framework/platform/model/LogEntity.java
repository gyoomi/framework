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
@DBTable(name = "fr_log")
public class LogEntity implements Serializable {

    /**
     * 主键
     */
    @DBColumn(isPrimaryKey = true, name = "id")
    private Long id;

    /**
     * 模块id
     */
    @DBColumn(name = "module_id")
    private Long moduleId;

    /**
     * 模块名称
     */
    @DBColumn(name = "module_name")
    private String moduleName;

    /**
     * 操作用户id
     */
    @DBColumn(name = "user_id")
    private Long userId;

    /**
     * 操作用户名
     */
    @DBColumn(name = "user_name")
    private String userName;

    /**
     * 日志内容
     */
    @DBColumn(name = "content")
    private String content;

    /**
     * 操作参数
     */
    @DBColumn(name = "parameters")
    private String parameters;


    /**
     * 创建时间
     */
    @DBColumn(name = "create_date")
    private Date createDate;

    /**
     * 操作ip
     */
    @DBColumn(name = "ip_address")
    private String ipAddress;

    /**
     * MAC地址
     */
    @DBColumn(name = "mac_address")
    private String macAddress;

    /**
     * 备注
     */
    @DBColumn(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id=" + id +
                ", moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", parameters='" + parameters + '\'' +
                ", createDate=" + createDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}