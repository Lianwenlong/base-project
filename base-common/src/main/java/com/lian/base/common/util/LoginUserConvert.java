package com.lian.base.common.util;

import cn.hutool.core.util.ObjectUtil;

import com.lian.base.common.model.user.IUser;
import com.lian.base.common.model.user.LoginUser;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录用户转换
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 12:03
 */
public class LoginUserConvert {
    /**
     * iUser转LoginUser
     *
     * @param iUser
     * @return
     */
    public static LoginUser iUser2LoginUser(IUser iUser) {
        if (ObjectUtil.isNull(iUser)) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setName(iUser.getUsername());
        loginUser.setRealName(iUser.getRealName());
        loginUser.setOrgId(iUser.getOrgId());
        if (ObjectUtil.isNotEmpty(iUser.getAuthorities())) {
            Set<Long> roleIds = iUser.getAuthorities()
                                     .stream()
                                     .map(grantedAuthority -> Long.parseLong(grantedAuthority.getAuthority()))
                                     .filter(ObjectUtil::isNotNull)
                                     .collect(Collectors.toSet());
            loginUser.setAuthorities(roleIds);
        }
        return loginUser;
    }
}
