package com.lian.base.common.annotation.exception;

import java.lang.annotation.*;

/**
 * <p>
 * 包装异常
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 10:25
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PackThrows {

    /**
     * 需要包装的异常类
     *
     * @return Class<? extends Throwable>[]
     */
    Class<? extends Throwable>[] value() default java.lang.Throwable.class;

    /**
     * 包装异常的方法，支持{@code spel}表达式；无返回值，参数为当前异常对象：#currentException
     * T(cn.hutool.core.util.ObjectUtil).isNotEmpty(#currentException)
     *
     * @return String
     */
    String wrap();
}
