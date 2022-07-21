package com.lian.base.common.exception;


import com.lian.base.common.constant.BaseConstant;
import com.lian.base.common.i18n.I18nTranslator;

/**
 * <p>
 * 全局异常封装
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 13:06
 */
public interface GlobalException {

    /**
     * 异常枚举编码
     *
     * @return 异常枚举编码
     */
    String name();

    /**
     * 获取异常枚举编码
     *
     * @return 异常枚举编码
     */
    default String getCode() {
        return name();
    }

    /**
     * 获取枚举异常状态码
     *
     * @return statusCode
     */
    int getStatus();

    /**
     * 国际化配置文件路径
     *
     * @return path
     */
    default String i18nPropPath() {
        // 国际化配置文件路径
        return BaseConstant.EXCEPTION_I18N_PROP_PATH + this.getClass().getSimpleName();
    }

    /**
     * 无国际化参数异常包装
     *
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeException() {
        String message = I18nTranslator.translate(i18nPropPath(), name());
        ExtRuntimeException exception = new ExtRuntimeException(message);
        exception.setCode(name());
        exception.setStatus(getStatus());
        return exception;
    }

    /**
     * 带国际化参数异常
     *
     * @param params 参数
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeException(Object... params) {
        String message = I18nTranslator.translate(i18nPropPath(), name(), params);
        ExtRuntimeException exception = new ExtRuntimeException(message);
        exception.setCode(name());
        exception.setStatus(getStatus());
        return exception;
    }

    /**
     * 无国际化参数异常包装
     *
     * @param cause 异常
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeException(Throwable cause) {
        String message = I18nTranslator.translate(i18nPropPath(), name());
        ExtRuntimeException exception = new ExtRuntimeException(message, cause);
        exception.setCode(name());
        exception.setStatus(getStatus());
        return exception;
    }

    /**
     * 有国际化参数异常包装
     *
     * @param cause  异常
     * @param params 国际化参数
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeException(Throwable cause, Object... params) {
        String message = I18nTranslator.translate(i18nPropPath(), name(), params);
        ExtRuntimeException exception = new ExtRuntimeException(message, cause);
        exception.setCode(name());
        exception.setStatus(getStatus());
        return exception;
    }

    /**
     * 自定义异常，可以设置更多参数
     *
     * @param cause              异常
     * @param enableSuppression  是否启用或禁用抑制
     * @param writableStackTrace 堆栈跟踪是否应该可写
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeCustomException(Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        String message = I18nTranslator.translate(i18nPropPath(), name());
        ExtRuntimeException exception = new ExtRuntimeException(message, cause, enableSuppression, writableStackTrace);
        exception.setCode(name());
        exception.setStatus(getStatus());
        return exception;
    }

    /**
     * 自定义异常，可以设置更多参数
     *
     * @param cause              异常
     * @param enableSuppression  是否启用或禁用抑制
     * @param writableStackTrace 堆栈跟踪是否应该可写
     * @param params             国际化参数
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeCustomException(Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, Object... params) {
        String message = I18nTranslator.translate(i18nPropPath(), name(), params);
        ExtRuntimeException exception = new ExtRuntimeException(message, cause, enableSuppression, writableStackTrace);
        exception.setCode(name());
        exception.setStatus(getStatus());
        return exception;
    }

    /**
     * 无国际化参数异常返回异常数据
     *
     * @param errorData 异常数据
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeExceptionAndErrorData(Object errorData) {
        String message = I18nTranslator.translate(i18nPropPath(), name());
        ExtRuntimeException exception = new ExtRuntimeException(message);
        exception.setCode(name());
        exception.setStatus(getStatus());
        exception.setErrorData(errorData);
        return exception;
    }

    /**
     * 有国际化参数异常返回异常数据
     *
     * @param errorData 异常数据
     * @param params    异常参数
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeExceptionAndErrorData(Object errorData, Object... params) {
        String message = I18nTranslator.translate(i18nPropPath(), name(), params);
        ExtRuntimeException runtimeException = new ExtRuntimeException(message);
        runtimeException.setCode(name());
        runtimeException.setStatus(getStatus());
        runtimeException.setErrorData(errorData);
        return runtimeException;
    }

    /**
     * 有国际化参数包装异常和返回异常数据
     *
     * @param cause     异常
     * @param errorData 异常数据
     * @param params    异常参数
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeExceptionAndErrorData(Throwable cause, Object errorData, Object... params) {
        String message = I18nTranslator.translate(i18nPropPath(), name(), params);
        ExtRuntimeException ex = new ExtRuntimeException(message, cause);
        ex.setCode(name());
        ex.setStatus(getStatus());
        ex.setErrorData(errorData);
        return ex;
    }

    /**
     * 自定义异常
     *
     * @param cause              异常
     * @param enableSuppression  是否启用或禁用抑制
     * @param writableStackTrace 堆栈跟踪是否应该可写
     * @param errorData          异常数据
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeCustomExceptionAndErrorData(Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, Object errorData) {
        String message = I18nTranslator.translate(i18nPropPath(), name());
        ExtRuntimeException ex = new ExtRuntimeException(message, cause, enableSuppression, writableStackTrace);
        ex.setCode(name());
        ex.setStatus(getStatus());
        ex.setErrorData(errorData);
        return ex;
    }

    /**
     * 自定义异常
     *
     * @param cause              异常
     * @param enableSuppression  是否启用或禁用抑制
     * @param writableStackTrace 堆栈跟踪是否应该可写
     * @param errorData          异常数据
     * @param params             国际化参数
     * @return ExtRuntimeException
     */
    default ExtRuntimeException runtimeCustomExceptionAndErrorData(Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, Object errorData, Object... params) {
        String message = I18nTranslator.translate(i18nPropPath(), name(), params);
        ExtRuntimeException ex = new ExtRuntimeException(message, cause, enableSuppression, writableStackTrace);
        ex.setCode(name());
        ex.setStatus(getStatus());
        ex.setErrorData(errorData);
        return ex;
    }
}
