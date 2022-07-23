package com.lian.base.service.student.dto;

import com.lian.base.common.model.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生信息传输对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 16:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("学生信息传输模型")
public class StudentDTO extends BaseVO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("地址")
    private String address;
}
