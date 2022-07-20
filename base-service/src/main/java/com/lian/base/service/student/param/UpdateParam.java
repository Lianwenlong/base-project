package com.lian.base.service.student.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 更新学生参数模型
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/11 18:16
 */
@ApiModel("更新学生参数模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateParam extends InsertParam {

    private static final long serialVersionUID = 8501609924193008264L;

    @NotNull
    @ApiModelProperty(value = "主键id", example = "1")
    private Long id;
}
