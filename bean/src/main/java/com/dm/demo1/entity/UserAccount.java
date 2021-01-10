package com.dm.demo1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ${author}
 * @since 2020-08-10
 */
@TableName("user_account")
public class UserAccount extends Model<UserAccount> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "account_id", type = IdType.AUTO)
    private Long accountId;

    /**
     * 用户类型。1 employers；2 Headhunters；3 job seekers；4 administrator
     */
    private Integer accountType;

    /**
     * 用户id
     */
    @TableField("invitedBy")
    private Long invitedBy;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0 未认证，1已认证
     */
    private Integer phoneConfirmation;

    /**
     * 0 未认证，1已认证
     */
    private Integer emailConfirmation;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 更改时间
     */
    private Long editDate;

    /**
     * 注册IP地址
     */
    private String createIp;

    /**
     * 帐户是否过期(1 未过期，0已过期)
     */
    private Boolean accountNonExpired;

    /**
     * 帐户是否被锁定(1 未锁定，0已锁定)
     */
    private Boolean accountNonLocked;

    /**
     * 密码是否过期(1 未过期，0已过期)
     */
    private Boolean credentialsNonExpired;

    /**
     * 帐户是否可用(1 可用，0 删除用户)
     */
    private Boolean enabled;

    /**
     * 基本角色。
招聘者角色： MANAGER， MEMBER

猎头角色： PERSONAL，MANAGER ，MEMBER

求职者角色：NORMAL， ABNORMAL

管理员权限：ALL， LIMITED

     */
    private String role;

    /**
     * 审核状态。0审核中，1已审核
     */
    private Integer status;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Long getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(Long invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneConfirmation() {
        return phoneConfirmation;
    }

    public void setPhoneConfirmation(Integer phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }

    public Integer getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(Integer emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getEditDate() {
        return editDate;
    }

    public void setEditDate(Long editDate) {
        this.editDate = editDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
        "accountId=" + accountId +
        ", accountType=" + accountType +
        ", invitedBy=" + invitedBy +
        ", username=" + username +
        ", password=" + password +
        ", phone=" + phone +
        ", email=" + email +
        ", phoneConfirmation=" + phoneConfirmation +
        ", emailConfirmation=" + emailConfirmation +
        ", createDate=" + createDate +
        ", editDate=" + editDate +
        ", createIp=" + createIp +
        ", accountNonExpired=" + accountNonExpired +
        ", accountNonLocked=" + accountNonLocked +
        ", credentialsNonExpired=" + credentialsNonExpired +
        ", enabled=" + enabled +
        ", role=" + role +
        ", status=" + status +
        "}";
    }
}
