package com.lian.base.common.service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 基础实体转换器 mapstruct实现
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 19:17
 */
public interface BaseConverter<In, Out> {

    /**
     * 输入转输出
     *
     * @param in 输入对象
     * @return Out 输出对象
     */
    Out in2Out(In in);


    /**
     * 输出转输入
     *
     * @param out 输出对象
     * @return In 输入对象
     */
    In out2In(Out out);


    /**
     * 输入转输出
     *
     * @param collection 数据集
     * @return List<Out>
     */
    List<Out> in2Out(List<In> collection);


    /**
     * 输出转输入
     *
     * @param collection 数据集
     * @return Set
     */
    List<In> out2In(List<Out> collection);


    /**
     * 输入转输出
     *
     * @param collection 数据集
     * @return Set<Out>
     */
    Set<Out> in2Out(Set<In> collection);


    /**
     * 输出转输入
     *
     * @param collection 数据集
     * @return Set
     */
    Set<In> out2In(Set<Out> collection);

}
