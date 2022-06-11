package com.lian.base.web.v1.student.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


/**
 * <p>
 * 展示层学生对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 18:41
 */

@Data
@ApiModel("学生模型")
public class StudentVO implements Serializable {

    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄", example = "28")
    private Integer age;

    @ApiModelProperty(value = "地址", example = "北京")
    private String address;
}
