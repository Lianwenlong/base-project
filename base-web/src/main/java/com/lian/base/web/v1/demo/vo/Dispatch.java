package com.lian.base.web.v1.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 对外分发传输对象
 * </p >
 *
 * @author LianWenLong
 * @since 2022/8/9 20:30
 */

@Data
@ApiModel("对外分发传输对象")
public class Dispatch<T> implements Serializable {

    private static final long serialVersionUID = -8390721780444242605L;

    @ApiModelProperty("操作类型：upsert - 增改、delete - 删除")
    private String operateType;

    @ApiModelProperty("模型标识")
    private String modelName;

    @ApiModelProperty("模型主键字段列表,有多个字段代表复合主键")
    private Set<String> primaryKeys;

    @ApiModelProperty("字段映射信息")
    private List<FieldMapping> mappings;

    @ApiModelProperty("主数据")
    private T data;
}
