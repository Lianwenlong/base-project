package com.lian.base.common.model;

import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 统一响应返回值模型
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/11 23:49
 */
@ApiModel("统一响应返回值模型")
public class GlobalResponse<T> {

    /**
     * 日志标识名
     */
    public static final String LOG_MARK_NAME = "global_auto_log_mark_id";

    
}
