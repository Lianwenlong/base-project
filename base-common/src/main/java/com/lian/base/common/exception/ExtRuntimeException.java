package com.lian.base.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 拓展异常类
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 13:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExtRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 4260557712812849677L;
    /**
     * 状态码
     */
    private int status;

    /**
     * 异常编码
     */
    private String code;

    /**
     * 异常数据
     */
    private Object errorData;


    public ExtRuntimeException() {
        super();
    }


    public ExtRuntimeException(String message) {
        super(message);
    }

    public ExtRuntimeException(Throwable cause) {
        super(cause);
        copyProperty(this, cause);
    }

    public ExtRuntimeException(String message, Throwable cause) {
        super(message, cause);
        copyProperty(this, cause);
    }

    public ExtRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        copyProperty(this, cause);
    }

    @Override
    public String toString() {
        return this.getLocalizedMessage();
    }

    /**
     * 复制属性
     *
     * @param runtimeException exception
     * @param cause            cause
     */
    private void copyProperty(ExtRuntimeException runtimeException, Throwable cause) {
        if (cause instanceof ExtRuntimeException) {
            ExtRuntimeException exception = (ExtRuntimeException) cause;
            runtimeException.setCode(exception.getCode());
            runtimeException.setStatus(exception.getStatus());
            runtimeException.setErrorData(exception.getErrorData());
        }
    }
}
