package com.lian.base.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lian.base.common.function.IField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * <p>
 * 枚举翻译VO对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 18:16
 */
@Data
@ApiModel(description = "枚举翻译通用类")
public class EnumVO<T> implements Serializable {

    private static final long serialVersionUID = -9205464253412072096L;

    @ApiModelProperty(value = "原始枚举类")
    @JsonIgnore
    private T source;

    @ApiModelProperty(value = "枚举编码")
    private String code;

    @ApiModelProperty(value = "枚举翻译")
    private String name;
    @ApiModelProperty(value = "枚举额外属性")
    private Map<String, Object> fields = new HashMap<>();

    /**
     * 设置属性值
     *
     * @param field 字段
     * @param value 字段值
     */
    public void setField(IField<T> field, Object value) {
        String fieldName = field.getFieldName(field);
        fields.put(fieldName, value);
    }

    /**
     * 获取属性值
     *
     * @param field 字段
     * @return value
     */
    public Object getFieldValue(IField<T> field) {
        String fieldName = field.getFieldName(field);
        return fields.get(fieldName);
    }
}
