package com.lian.base.common.model.page;

import cn.hutool.core.text.StrJoiner;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.lian.base.common.exception.BaseException;
import com.lian.base.common.function.IField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * <p>
 * 查询分页
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 18:40
 */
@ApiModel("查询分页对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryPager extends Pager {

    private static final long serialVersionUID = 2245046050161858568L;

    @ApiModelProperty(value = "查询集合信息")
    private List<PagerQuery> queries = new ArrayList<>();

    @ApiModelProperty(value = "排序集合信息")
    private List<PagerOrder> orders = new ArrayList<>();

    @ApiModel(description = "查询类型")
    public enum QueryType {
        /**
         * 等于
         */
        eq,
        /**
         * 不等于
         */
        ne,
        /**
         * 大于
         */
        gt,
        /**
         * 小于
         */
        lt,
        /**
         * 大于等于
         */
        ge,
        /**
         * 小于等于
         */
        le,
        /**
         * 全模糊
         */
        like,
        /**
         * 模糊之外
         */
        notLike,
        /**
         * 左模糊
         */
        likeLeft,
        /**
         * 右模糊
         */
        likeRight,
        /**
         * 两者之间
         */
        between,
        /**
         * 不在两者之间
         */
        notBetween,
        /**
         * 包含
         */
        in,
        /**
         * 不包含
         */
        notIn
    }

    @Data
    @ApiModel(description = "查询条件对象")
    public static class PagerQuery implements Serializable {

        private static final long serialVersionUID = -870673551074929151L;

        @ApiModelProperty(value = "需要查询的字段")
        private String field;

        @ApiModelProperty(value = "查询类型")
        private QueryType queryType;

        @ApiModelProperty(value = "查询数据")
        private Object queryData;

        public void setQueryData(Object queryData) {
            if (!ArrayUtil.contains(
                    new QueryType[]{QueryType.in, QueryType.notIn, QueryType.between, QueryType.notBetween},
                    queryType)) {
                this.queryData = queryData;
                return;
            }
            if (queryData instanceof CharSequence) {
                if (!JSONUtil.isTypeJSONArray((String) queryData)) {
                    throw new RuntimeException("参数值必需是一个数组对象");
                }
                this.queryData = queryData;
                return;
            }
            if (!ArrayUtil.isArray(queryData)) {
                throw new RuntimeException("参数值必需是一个数组对象");
            }
            this.queryData = queryData;
        }
    }

    @Data
    @ApiModel(description = "查询排序对象")
    public static class PagerOrder implements Serializable {
        private static final long serialVersionUID = -6070389557489784194L;

        @ApiModelProperty(value = "需要进行排序的字段")
        private String column;

        @ApiModelProperty(value = "是否正序排列，默认:true")
        private boolean asc = true;
    }


    /**
     * 根据类型和属性名查找查询对象
     *
     * @param type     查询类型
     * @param property 查询属性
     */
    private Optional<PagerQuery> findPagerQuery(QueryType type, String property) {
        List<PagerQuery> queries = this.getQueries();
        if (ObjectUtil.isEmpty(queries)) {
            return Optional.empty();
        }
        return queries.parallelStream().filter(pagerQuery -> {
            //查询类型
            QueryType queryType = pagerQuery.getQueryType();
            if (type != queryType) {
                return false;
            }
            String field = pagerQuery.getField();
            return ObjectUtil.equal(property, field);
        }).findFirst();
    }

    /**
     * 查找排序
     *
     * @param property 属性
     * @param asc      是否升序
     */
    private Optional<PagerOrder> findPagerOrder(String property, boolean asc) {
        List<PagerOrder> orders = this.getOrders();
        if (ObjectUtil.isEmpty(orders)) {
            return Optional.empty();
        }
        return orders.parallelStream().filter(pagerOrder -> {
            String column = pagerOrder.getColumn();
            if (ObjectUtil.notEqual(column, property)) {
                return false;
            }
            return ObjectUtil.equal(asc, pagerOrder.isAsc());
        }).findFirst();
    }

    /**
     * 等于
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager eq(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.eq, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager eq(IField<T> iField, Consumer<Object> consumer) {
        return eq(iField.getFieldName(iField), consumer);
    }

    /**
     * 不等于
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager ne(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.ne, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager ne(IField<T> iField, Consumer<Object> consumer) {
        return ne(iField.getFieldName(iField), consumer);
    }

    /**
     * 大于
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager gt(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.gt, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager gt(IField<T> iField, Consumer<Object> consumer) {
        return gt(iField.getFieldName(iField), consumer);
    }

    /**
     * 小于
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager lt(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.lt, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager lt(IField<T> iField, Consumer<Object> consumer) {
        return lt(iField.getFieldName(iField), consumer);
    }

    /**
     * 大于等于
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager ge(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.ge, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager ge(IField<T> iField, Consumer<Object> consumer) {
        return ge(iField.getFieldName(iField), consumer);
    }

    /**
     * 小于等于
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager le(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.le, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager le(IField<T> iField, Consumer<Object> consumer) {
        return le(iField.getFieldName(iField), consumer);
    }

    /**
     * 全模糊
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager like(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.like, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager like(IField<T> iField, Consumer<Object> consumer) {
        return like(iField.getFieldName(iField), consumer);
    }

    /**
     * 模糊之外
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager notLike(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.notLike, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager notLike(IField<T> iField, Consumer<Object> consumer) {
        return notLike(iField.getFieldName(iField), consumer);
    }

    /**
     * 左模糊
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager likeLeft(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.likeLeft, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager likeLeft(IField<T> iField, Consumer<Object> consumer) {
        return likeLeft(iField.getFieldName(iField), consumer);
    }

    /**
     * 右模糊
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager likeRight(String property, Consumer<Object> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.likeRight, property);
        pagerQuery.ifPresent(query -> consumer.accept(query.queryData));
        return this;
    }

    public <T> QueryPager likeRight(IField<T> iField, Consumer<Object> consumer) {
        return likeRight(iField.getFieldName(iField), consumer);
    }

    /**
     * 两者之间
     *
     * @param property   字段名
     * @param biConsumer 操作
     */
    @SuppressWarnings("Duplicates")
    public QueryPager between(String property, BiConsumer<Object, Object> biConsumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.between, property);
        pagerQuery.ifPresent(query -> {
            JSONArray jsonArray = JSONUtil.parseArray(query.queryData);
            if (jsonArray.isEmpty()) {
                return;
            }
            switch (jsonArray.size()) {
                case 1:
                    biConsumer.accept(jsonArray.getObj(0), null);
                    break;
                case 2:
                    biConsumer.accept(jsonArray.getObj(0), jsonArray.get(1));
                    break;
                default:
                    throw BaseException.UNSUPPORTED_OPERATIONS.runtimeException();
            }
        });
        return this;
    }

    public <T> QueryPager between(IField<T> iField, BiConsumer<Object, Object> biConsumer) {
        return between(iField.getFieldName(iField), biConsumer);
    }

    /**
     * 两者之外
     *
     * @param property   字段名
     * @param biConsumer 操作
     */
    @SuppressWarnings("Duplicates")
    public QueryPager notBetween(String property, BiConsumer<Object, Object> biConsumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.notBetween, property);
        pagerQuery.ifPresent(query -> {
            JSONArray jsonArray = JSONUtil.parseArray(query.queryData);
            if (jsonArray.isEmpty()) {
                return;
            }
            switch (jsonArray.size()) {
                case 1:
                    biConsumer.accept(jsonArray.getObj(0), null);
                    break;
                case 2:
                    biConsumer.accept(jsonArray.getObj(0), jsonArray.get(1));
                    break;
                default:
                    throw BaseException.UNSUPPORTED_OPERATIONS.runtimeException();
            }
        });
        return this;
    }

    public <T> QueryPager notBetween(IField<T> iField, BiConsumer<Object, Object> biConsumer) {
        return notBetween(iField.getFieldName(iField), biConsumer);
    }

    /**
     * 包含
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager in(String property, Consumer<Object[]> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.in, property);
        pagerQuery.ifPresent(query -> {
            JSONArray jsonArray = JSONUtil.parseArray(query.queryData);
            if (jsonArray.isEmpty()) {
                return;
            }
            consumer.accept(jsonArray.toArray());
        });
        return this;
    }

    public <T> QueryPager in(IField<T> iField, Consumer<Object[]> consumer) {
        return in(iField.getFieldName(iField), consumer);
    }

    /**
     * 不包含
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager notIn(String property, Consumer<Object[]> consumer) {
        Optional<PagerQuery> pagerQuery = findPagerQuery(QueryType.notIn, property);
        pagerQuery.ifPresent(query -> {
            JSONArray jsonArray = JSONUtil.parseArray(query.queryData);
            if (jsonArray.isEmpty()) {
                return;
            }
            consumer.accept(jsonArray.toArray());
        });
        return this;
    }

    public <T> QueryPager notIn(IField<T> iField, Consumer<Object[]> consumer) {
        return notIn(iField.getFieldName(iField), consumer);
    }

    /**
     * 正序
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager orderByAsc(String property, Consumer<String> consumer) {
        Optional<PagerOrder> pagerQuery = findPagerOrder(property, true);
        pagerQuery.ifPresent(order -> consumer.accept(order.getColumn()));
        return this;
    }

    public <T> QueryPager orderByAsc(IField<T> iField, Consumer<String> consumer) {
        return orderByAsc(iField.getFieldName(iField), consumer);
    }

    /**
     * 倒序
     *
     * @param property 字段名
     * @param consumer 操作
     */
    public QueryPager orderByDesc(String property, Consumer<String> consumer) {
        Optional<PagerOrder> pagerQuery = findPagerOrder(property, false);
        pagerQuery.ifPresent(order -> consumer.accept(order.getColumn()));
        return this;
    }

    public <T> QueryPager orderByDesc(IField<T> iField, Consumer<String> consumer) {
        return orderByDesc(iField.getFieldName(iField), consumer);
    }

    /**
     * 转查询字符串
     *
     * @return String
     */
    public String toQueryParams() {
        List<String> result = new ArrayList<>();
        result.add("size=" + this.getSize());
        result.add("current=" + this.getCurrent());
        result.add("searchCount=" + this.isSearchCount());
        //处理查询条件
        for (int i = 0; i < queries.size(); i++) {
            PagerQuery pagerQuery = queries.get(i);
            String field = pagerQuery.getField();
            QueryType type = pagerQuery.getQueryType();
            Object queryData = pagerQuery.getQueryData();
            result.add("queries[" + i + "].field=" + field);
            result.add("queries[" + i + "].queryType=" + type);
            result.add("queries[" + i + "].queryData=" + ArrayUtil.toString(queryData));
        }
        //处理排序
        for (int i = 0; i < orders.size(); i++) {
            PagerOrder pagerOrder = orders.get(i);
            String column = pagerOrder.getColumn();
            boolean asc = pagerOrder.isAsc();
            result.add("orders[" + i + "].column=" + column);
            result.add("orders[" + i + "].asc=" + asc);
        }
        return StrJoiner.of("&", "?", null)
                        // 每个元素都添加前后缀
                        .setWrapElement(false).append(result.toArray()).toString();
    }

    /**
     * 转查询map对象
     */
    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.set("size", String.valueOf(this.getSize()));
        multiValueMap.set("current", String.valueOf(this.getCurrent()));
        multiValueMap.set("searchCount", String.valueOf(this.isSearchCount()));
        //处理查询条件
        for (int i = 0; i < queries.size(); i++) {
            PagerQuery pagerQuery = queries.get(i);
            String field = pagerQuery.getField();
            QueryType type = pagerQuery.getQueryType();
            Object queryData = pagerQuery.getQueryData();
            multiValueMap.set("queries[" + i + "].field", field);
            multiValueMap.set("queries[" + i + "].queryType", type.name());
            multiValueMap.set("queries[" + i + "].queryData", ArrayUtil.toString(queryData));
        }
        //处理排序
        for (int i = 0; i < orders.size(); i++) {
            PagerOrder pagerOrder = orders.get(i);
            String column = pagerOrder.getColumn();
            boolean asc = pagerOrder.isAsc();
            multiValueMap.set("orders[" + i + "].column", column);
            multiValueMap.set("orders[" + i + "].asc", Boolean.toString(asc));
        }
        return multiValueMap;
    }

    /**
     * 新增一个查询条件
     *
     * @param pagerQuery 查询条件
     */
    public void addPagerQuery(PagerQuery pagerQuery) {
        this.queries.add(pagerQuery);
    }

    public void addPagerQuery(Supplier<PagerQuery> supplier) {
        if (ObjectUtil.isNull(supplier)) {
            return;
        }
        this.queries.add(supplier.get());
    }

    /**
     * 新增一个排序
     *
     * @param pagerOrder 排序条件
     */
    public void addPagerOrder(PagerOrder pagerOrder) {
        this.orders.add(pagerOrder);
    }

    public void addPagerOrder(Supplier<PagerOrder> supplier) {
        if (ObjectUtil.isNull(supplier)) {
            return;
        }
        this.orders.add(supplier.get());
    }
}
