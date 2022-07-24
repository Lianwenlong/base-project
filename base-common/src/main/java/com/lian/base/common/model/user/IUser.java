package com.lian.base.common.model.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * <p>
 * TODO(注释说明)
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 11:58
 */
public class IUser extends User {

    private static final long serialVersionUID = -7642459580562173603L;
    /**
     * 真实名称
     */
    private final String realName;
    /**
     * 所属组织ID
     */
    private final Long orgId;

    public IUser(String username, String password, String realName, Long orgId,
                 Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.realName = realName;
        this.orgId = orgId;
    }


    public String getRealName() {
        return realName;
    }

    public Long getOrgId() {
        return orgId;
    }
}
