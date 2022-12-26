package com.lian.base.web.v1.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 字段映射DTO
 * </p >
 *
 * @author LianWenLong
 * @since 2022/8/9 20:40
 */
@Data
@ApiModel("字段映射DTO")
public class FieldMapping implements Serializable {

    private static final long serialVersionUID = 3207007876182691519L;

    @ApiModelProperty("字段")
    private String name;

    @ApiModelProperty("映射字段")
    private String mappingName;
}
