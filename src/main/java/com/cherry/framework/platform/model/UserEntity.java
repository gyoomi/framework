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
@DBTable(name = "fr_user")
public class UserEntity implements Serializable {

    /**
     * 主键
     */
    @DBColumn(isPrimaryKey = true, name = "id")
    private Long id;

    /**
     * 登录名
     */
    @DBColumn(name = "login_name")
    private String loginName;

    /**
     * 用户名
     */
    @DBColumn(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @DBColumn(name = "password")
    private String password;

    /**
     * 用户头像url链接地址
     */
    @DBColumn(name = "head_img_url")
    private String headImgUrl;

    /**
     * 性别
     */
    @DBColumn(name = "gender")
    private Integer gender;

    /**
     * 手机号
     */
    @DBColumn(name = "phone_number")
    private String phoneNumber;


    /**
     * 电子邮箱
     */
    @DBColumn(name = "email")
    private String email;

    /**
     * 用户状态：1 - 启用；其他 - 停用
     */
    @DBColumn(name = "status")
    private Integer status;

    /**
     * 用户类型：1 - 后台；2 - 钉钉
     */
    @DBColumn(name = "type")
    private Integer type;

    /**
     * 登录绑定方式
     */
    @DBColumn(name = "bind_type")
    private Integer bindType;

    /**
     * IP/MAC地址
     */
    @DBColumn(name = "ip_or_mac")
    private String ipOrMac;

    /**
     * 备用信息1
     */
    @DBColumn(name = "item1")
    private String item1;

    /**
     * 备用信息2
     */
    @DBColumn(name = "item2")
    private String item2;

    /**
     * 备用信息3
     */
    @DBColumn(name = "item3")
    private String item3;

    /**
     * 备用信息4
     */
    @DBColumn(name = "item4")
    private String item4;

    /**
     * 备用信息5
     */
    @DBColumn(name = "item5")
    private String item5;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getIpOrMac() {
        return ipOrMac;
    }

    public void setIpOrMac(String ipOrMac) {
        this.ipOrMac = ipOrMac;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
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
        return "UserEntity{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", bindType=" + bindType +
                ", ipOrMac='" + ipOrMac + '\'' +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", item5='" + item5 + '\'' +
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