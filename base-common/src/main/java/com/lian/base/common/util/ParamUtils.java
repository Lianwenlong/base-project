package com.lian.base.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.lian.base.common.exception.BaseException;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 参数校验工具类
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 17:21
 */
public class ParamUtils {
    private ParamUtils() {

    }

    /**
     * 主键非空校验
     *
     * @param id          主键id
     * @param idFieldName 主键字段名
     */
    public static void idNotNullValidate(Long id, String idFieldName) {
        if (Objects.isNull(id)) {
            throw BaseException.PARAM_NOT_NULL.runtimeException(idFieldName);
        }
    }

    /**
     * 数据传输对象非空校验
     *
     * @param dto          传输对象
     * @param objClassName 传输对象类名
     */
    public static void dtoNotNullValidate(Object dto, String objClassName) {
        if (Objects.isNull(dto)) {
            throw BaseException.PARAM_NOT_NULL.runtimeException(objClassName);
        }
    }

    /**
     * List 对象非空校验
     *
     * @param list      list对象
     * @param paramName 参数名称
     */
    public static void listNotEmptyValidate(List<?> list, String paramName) {
        if (ObjectUtil.isEmpty(list)) {
            throw BaseException.PARAM_NOT_NULL.runtimeException(paramName);
        }
    }

    /**
     * List 对象非空校验
     *
     * @param set       list对象
     * @param paramName 参数名称
     */
    public static void setNotEmptyValidate(Set<?> set, String paramName) {
        if (ObjectUtil.isEmpty(set)) {
            throw BaseException.PARAM_NOT_NULL.runtimeException(paramName);
        }
    }
}
