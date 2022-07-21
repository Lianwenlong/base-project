package com.lian.base.common.exception;

/**
 * <p>
 * 通用异常枚举
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 13:08
 */
public enum BaseException implements GlobalException {
    /**
     * 无权限
     */
    UNAUTHORIZED(401),
    /**
     * 未授权
     */
    FORBIDDEN(403),
    /**
     * 404找不到
     */
    HTTP_NOT_FOUND(404),
    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE(400),
    /**
     * 非法参数
     */
    ILLEGAL_PARAM(400),
    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(400),

    /**
     * 分布式ID取号异常
     */
    DISPERSED_ID_ERROR(500),
    /**
     * 未知异常
     */
    UNKNOWN(400),
    /**
     * 参数不能为空
     */
    PARAM_NOT_NULL(400),
    /**
     * 请求超时
     */
    TIME_OUT(500),
    /**
     * 通用自定义异常,异常信息传入
     */
    COMMON_CUSTOM(500);

    private final int status;

    BaseException(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
