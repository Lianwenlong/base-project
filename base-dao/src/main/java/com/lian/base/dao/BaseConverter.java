package com.lian.base.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.base.common.model.page.QueryPager;
import com.lian.base.common.model.page.ResultPager;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapping;

/**
 * <p>
 * 实体转换器 借助mapstruct实现
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 19:17
 */
public interface BaseConverter<VO, DO> {
    /**
     * DO 转 VO
     *
     * @param doo DO
     * @return VO
     */
    VO do2vo(DO doo);

    /**
     * VO 转 DO
     *
     * @param vo VO
     * @return DO
     */
    DO vo2do(VO vo);

    /**
     * po集合转vo集合
     *
     * @param collection 集合
     */
    List<VO> do2vo(List<DO> collection);

    /**
     * VO集合转DO集合
     *
     * @param collection 集合
     */
    List<DO> vo2do(List<VO> collection);

    /**
     * DO集合转VO集合
     *
     * @param collection 集合
     */
    Set<VO> do2vo(Set<DO> collection);

    /**
     * VO集合转DO集合
     *
     * @param collection 集合
     */
    Set<DO> vo2do(Set<VO> collection);

    /**
     * 查询分页转mybatis-plus分页
     *
     * @param pager 分页对象
     */
    @Mapping(target = "orders", ignore = true)
    Page<DO> queryPager2Page(QueryPager pager);

    /**
     * Mybatis分页 转 结果分页
     *
     * @param page 分页对象
     */
    ResultPager<VO> page2ResultPager(Page<DO> page);
}
