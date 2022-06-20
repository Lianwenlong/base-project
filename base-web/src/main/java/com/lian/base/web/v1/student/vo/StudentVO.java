package com.lian.base.web.v1.student.vo;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
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
@ExcelSheet(name = "学生信息", backColor = AlanColor.BROWN, font = "宋体")
public class StudentVO implements Serializable {

    private static final long serialVersionUID = -2526566067256734024L;

    @ExcelColumn(name = "编号", index = "0")
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    @ExcelColumn(name = "姓名", index = "1")
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ExcelColumn(name = "年龄", index = "2")
    @ApiModelProperty(value = "年龄", example = "28")
    private Integer age;

    @ExcelColumn(name = "地址", index = "3")
    @ApiModelProperty(value = "地址", example = "北京")
    private String address;
}

