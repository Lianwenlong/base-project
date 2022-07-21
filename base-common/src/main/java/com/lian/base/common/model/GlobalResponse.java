package com.lian.base.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 统一返回数据封装类
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/11 23:49
 */
@ApiModel("统一响应返回值模型")
@Data
@NoArgsConstructor
public class GlobalResponse<T> {

    /**
     * 日志标识名
     */
    public static final String LOG_MARK_NAME = "global_auto_log_mark_id";

    @ApiModelProperty(value = "错误码")
    private String errorCode;

    @ApiModelProperty(value = "提示信息")
    private Object errorMessage;

    @ApiModelProperty(value = "成功与否")
    private boolean success;

    @ApiModelProperty(value = "响应时间")
    private String timesTamp;

    @ApiModelProperty(value = "对应日志标识ID")
    private String logMarkId;

    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 通用响应实体构造函数
     *
     * @param data      响应数据
     * @param logMarkId 日志标识ID
     * @param success   成功与否
     */
    private GlobalResponse(T data, String logMarkId, boolean success) {
        this.timesTamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        this.data = data;
        this.logMarkId = logMarkId;
        this.success = success;
    }

    /**
     * 成功响应
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return GlobalResponse
     */
    public static <T> GlobalResponse<T> success(T data) {
        return GlobalResponse.success(data, null);
    }

    /**
     * 成功响应
     *
     * @param data      响应数据
     * @param logMarkId 日志标识ID
     * @param <T>       响应数据类型
     * @return GlobalResponse
     */
    public static <T> GlobalResponse<T> success(T data, String logMarkId) {
        return new GlobalResponse<T>(data, logMarkId, true);
    }


    /**
     * 返回失败的实体
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @return GlobalResponse
     */
    public static <T> GlobalResponse<T> fail(String errorCode, Object errorMessage) {
        return GlobalResponse.fail(errorCode, errorMessage, null);
    }

    /**
     * 返回带有日志标示ID的失败实体
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @param logMarkId    日志标识ID
     * @param <T>          响应数据类型
     * @return GlobalResponse
     */
    public static <T> GlobalResponse<T> fail(String errorCode, Object errorMessage, String logMarkId) {
        GlobalResponse<T> result = new GlobalResponse<T>(null, logMarkId, false);
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return result;
    }

    /**
     * 返回失败实体，并且可以返回引起失败的具体数据
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @param errorData    引起异常的数据
     * @param logMarkId    日志标识ID
     * @param <T>          响应数据类型
     * @return GlobalResponse
     */
    public static <T> GlobalResponse<T> fail(String errorCode, Object errorMessage, T errorData, String logMarkId) {
        GlobalResponse<T> result = new GlobalResponse<T>(errorData, logMarkId, false);
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return result;
    }
}
