package com.lian.base.common.model;


import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 查询分页结果对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 18:41
 */
@ApiModel(description = "查询分页结果对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultPager<T> extends Pager {

    private static final long serialVersionUID = -6417226931367179469L;

    @ApiModelProperty(value = "结果数据列表")
    private List<T> records = Collections.emptyList();

    @ApiModelProperty(value = "总数")
    private long total = 0;

    @ApiModelProperty(value = "是否有下一页,{@code searchCount=false}时可自定义")
    private boolean hasNext;

    @ApiModelProperty(value = "总页数")
    public long getTotalPage() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = total / getSize();
        if (total % getSize() != 0) {
            pages++;
        }
        return pages;
    }


    @ApiModelProperty(value = "是否存在上一页")
    public boolean isHasPrevious() {
        return getCurrent() > 1;
    }

    /**
     * 是否存在下一页
     */
    public boolean isHasNext() {
        if (isSearchCount()) {
            return this.getCurrent() < this.getTotalPage();
        }
        return this.hasNext;
    }

    /**
     * 根据下标获取记录
     *
     * @param i 下标
     */
    public T getRecord(int i) {
        if (ObjectUtil.isEmpty(records)) {
            return null;
        }
        return records.get(i);
    }

    /**
     * 根据条件获取第一个匹配到的值
     *
     * @param predicate predicate
     */
    public Optional<T> getRecordFirst(Predicate<T> predicate) {
        if (ObjectUtil.isEmpty(records)) {
            return Optional.empty();
        }
        return records.stream().filter(predicate).findFirst();
    }

    /**
     * 根据条件获取任意一个匹配到的值
     *
     * @param predicate predicate
     */
    public Optional<T> getRecordAny(Predicate<T> predicate) {
        if (ObjectUtil.isEmpty(records)) {
            return Optional.empty();
        }
        return records.stream().filter(predicate).findAny();
    }
}
