package com.lian.base.web.v1.student.vo;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * 学生信息传输对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 16:31
 */
@Data
@ApiModel("学生展示模型")
@ExcelSheet(name = "学生信息", backColor = AlanColor.LIGHT_YELLOW, font = "宋体")
public class StudentVO implements Serializable {

    private static final long serialVersionUID = 6838752184520907977L;

    /**
     * Excel数据导入时使用
     */
    @JsonIgnore
    private Integer rowIndex;

    @ExcelColumn(name = "学号", index = "0")
    @ApiModelProperty(value = "主键")
    private Long id;

    @ExcelColumn(name = "姓名", index = "1")
    @ApiModelProperty("姓名")
    private String name;

    @ExcelColumn(name = "年龄", index = "2")
    @ApiModelProperty("年龄")
    private Integer age;

    @ExcelColumn(name = "地址", index = "3")
    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelColumn(isExist = false, index = "4")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @ExcelColumn(isExist = false, index = "5")
    private String creator;

    @ApiModelProperty(value = "创建人翻译")
    @ExcelColumn(isExist = false, index = "6")
    private String creatorName;

    @ApiModelProperty(value = "更新时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelColumn(isExist = false, index = "7")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "更新人")
    @ExcelColumn(isExist = false, index = "8")
    private String modifier;

    @ApiModelProperty(value = "更新人翻译")
    @ExcelColumn(isExist = false, index = "9")
    private String modifierName;
}
