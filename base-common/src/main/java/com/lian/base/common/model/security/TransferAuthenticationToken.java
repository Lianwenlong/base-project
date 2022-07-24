package com.lian.base.common.model.security;

import com.lian.base.common.model.user.LoginUser;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 支持传输的认证模式
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 12:04
 */
public class TransferAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 2867025088749273719L;
    private final LoginUser principal;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     * @param principal   用户
     */
    public TransferAuthenticationToken(Collection<? extends GrantedAuthority> authorities, LoginUser principal) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
