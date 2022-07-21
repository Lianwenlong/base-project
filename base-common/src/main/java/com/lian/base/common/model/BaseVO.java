package com.lian.base.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 基础VO对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/20 23:15
 */
@Data
@ApiModel(description = "基础VO对象")

public class BaseVO implements Serializable {

    private static final long serialVersionUID = 6853396298736641315L;

    @ApiModelProperty(value = "主键")
    private Long id;


    @ApiModelProperty(value = "创建时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "创建人翻译")
    private String creatorName;


    @ApiModelProperty(value = "更新时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;


    @ApiModelProperty(value = "更新人")
    private String modifier;

    @ApiModelProperty(value = "更新人翻译")
    private String modifierName;

}
