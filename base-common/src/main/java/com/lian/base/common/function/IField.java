package com.lian.base.common.function;

import com.lian.base.common.exception.BaseException;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * 获取字段名
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 16:07
 */
@FunctionalInterface
public interface IField<T> extends Serializable {

    /**
     * 获取字段
     *
     * @param field 字段名
     * @return 字段
     */
    Object get(T field);


    default String getFieldName(IField<T> iField) {
        try {
            Method method = iField.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(iField);
            String getter = serializedLambda.getImplMethodName();
            return Introspector.decapitalize(getter.replace("get", ""));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw BaseException.COMMON_CUSTOM.runtimeException(e.getMessage());
        }
    }
}
