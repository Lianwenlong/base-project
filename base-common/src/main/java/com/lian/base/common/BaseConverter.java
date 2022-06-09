package com.lian.base.common;

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
public interface BaseConverter<S, T> {

    /**
     * 源对象转目标对象
     *
     * @param source 源对象
     * @return T 目标对象
     */
    T so2to(S source);


    /**
     * 目标对象转源对象
     *
     * @param target 目标对象
     * @return S 源对象
     */
    S to2so(T target);


    /**
     * source集合转target集合
     *
     * @param collection 数据集
     * @return List<T>
     */
    List<T> so2to(List<S> collection);


    /**
     * target集合转source集合
     *
     * @param collection 数据集
     * @return Set
     */
    List<S> to2so(List<T> collection);


    /**
     * source集合转target集合
     *
     * @param collection 数据集
     * @return Set<T>
     */
    Set<T> so2to(Set<S> collection);


    /**
     * target集合转source集合
     *
     * @param collection 数据集
     * @return Set
     */
    Set<S> to2so(Set<T> collection);
}
