package com.lian.base.service.student.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 新增学生参数模型
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:35
 */
@Data
@ApiModel("新增学生参数模型")
public class InsertParam implements Serializable {

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄", example = "19")
    private Integer age;

    @ApiModelProperty(value = "地址", example = "北京")
    private String address;
}
