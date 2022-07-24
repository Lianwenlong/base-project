package com.lian.base.common.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * <p>
 * 登录用户信息
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 11:48
 */
@Data
@ApiModel(value = "登录用户信息")
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 3162922636181384406L;

    @ApiModelProperty("登录名")
    private String name;

    @ApiModelProperty("真实名称")
    private String realName;

    @ApiModelProperty("所属组织ID")
    private Long orgId;

    @ApiModelProperty("用户有权限的角色列表")
    private Set<Long> authorities;
}
