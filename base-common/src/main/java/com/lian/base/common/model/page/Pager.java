package com.lian.base.common.model.page;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 通用分页对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 18:36
 */
@Data
@ApiModel(description = "通用分页对象")
public abstract class Pager implements Serializable {

    private static final long serialVersionUID = -2010745492794160734L;

    /**
     * 单页最大数据条数
     */
    private static final int MAX_SIZE = 1000;

    @ApiModelProperty(value = "每页显示条数，默认 10")
    private long size = 10;


    @ApiModelProperty(value = "当前页")
    private long current = 1;

    @ApiModelProperty(value = "是否进行 count 查询")
    private boolean searchCount = true;


    /**
     * 当前页不能小于1
     *
     * @param current 当前页码
     */
    public void setCurrent(long current) {
        if (current > 1) {
            this.current = current;
        }
    }

    /**
     * 每页显示数据条数不能大于1000条
     *
     * @param size 条数
     */
    public void setSize(long size) {
        if (size <= MAX_SIZE) {
            this.size = size;
        }
    }
}
