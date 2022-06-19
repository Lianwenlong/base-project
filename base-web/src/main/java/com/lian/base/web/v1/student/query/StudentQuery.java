package com.lian.base.web.v1.student.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 学生查询对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/11 16:30
 */
@Data
@ApiModel("学生查询对象")
public class StudentQuery implements Serializable {

    private static final long serialVersionUID = -3833191389661245654L;
    
    @ApiModelProperty(value = "查询学生姓名", example = "张")
    private String name;

}
