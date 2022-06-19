package com.lian.base.service.student.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 学生对象DTO模型
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:35
 */
@Data
@ApiModel("学生信息传输对象")
public class StudentDTO implements Serializable {

    private static final long serialVersionUID = 3148763067592734862L;
    
    @ApiModelProperty(value = "主键id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄", example = "19")
    private Integer age;

    @ApiModelProperty(value = "地址", example = "北京")
    private String address;
}
