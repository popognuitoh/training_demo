package com.dm.demo1.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * ---------------------------
 * (MobileAuthenticationToken) 拼装手机登陆token
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
public class MobileAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal; //认证前是手机号码  认证后是用户信息

    /**
     * 认证之前使用的构造方法  会标识未认证
     * 存放的是mobile  手机号
     *
     */
    public MobileAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }


    /**
     * 认证通过后 会重新创建  MobileAuthenticationToken实例来储存认证信息
     * @param principal 接受的数据库查出来的用户信息
     * @param authorities 资源权限
     */
    public MobileAuthenticationToken(Object principal,
        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    /**
     * 因为是父类中的抽象方法 所以要实现 返回空即可
     * @return
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
