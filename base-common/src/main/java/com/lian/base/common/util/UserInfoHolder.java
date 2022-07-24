package com.lian.base.common.util;

import cn.hutool.core.util.ObjectUtil;

import com.lian.base.common.model.user.IUser;
import com.lian.base.common.model.user.LoginUser;

import com.lian.base.common.model.security.TransferAuthenticationToken;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * 用户信息持有类
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 11:46
 */
public class UserInfoHolder {
    private UserInfoHolder() {

    }

    public static Optional<LoginUser> getCurrentUser() {
        // 获取安全框架中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtil.isNull(authentication)) {
            return Optional.empty();
        }
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            IUser iUser = (IUser) authentication.getPrincipal();
            return Optional.ofNullable(LoginUserConvert.iUser2LoginUser(iUser));
        }
        if (authentication instanceof TransferAuthenticationToken) {
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            return Optional.ofNullable(loginUser);
        }
        return Optional.empty();
    }
}
