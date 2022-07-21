package com.lian.base.common.util;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lian.base.common.exception.ExtRuntimeException;
import com.lian.base.common.model.GlobalResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

/**
 * <p>
 * Response 工具类
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 17:42
 */
@Slf4j
public class ResponseUtils {

    private static final ObjectMapper OBJECT_MAPPER = SpringUtil.getBean(ObjectMapper.class);

    private ResponseUtils() {

    }

    /**
     * 重定向到错误页面
     *
     * @param response  response响应
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static void redirectErrorHtml(HttpServletResponse response, int errorCode, String message) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        try {
            response.sendError(errorCode, message);
        } catch (IOException e) {
            throw new RuntimeException("IO异常！！！");
        }
    }


    /**
     * 通过response 返回错误信息
     *
     * @param response     response对象
     * @param errorMessage 错误信息
     */
    public static void outputErrorInfo(HttpServletResponse response, String errorCode, Object errorMessage) {
        log.error(errorMessage.toString());
        int status = response.getStatus();
        if (status == HttpStatus.HTTP_OK) {
            response.setStatus(500);
        }
        send(response, GlobalResponse.fail(errorCode, errorMessage));
    }

    /**
     * 返回异常信息
     *
     * @param response response
     * @param ex       异常对象
     */
    public static void outputException(HttpServletResponse response, ExtRuntimeException ex) {
        if (ex == null) {
            return;
        }
        log.error(ex.getCode(), ex);
        response.setStatus(ex.getStatus());
        send(response, GlobalResponse.fail(ex.getCode(), ex.getMessage()));
    }

    /**
     * 通过response返回数据
     *
     * @param response response
     * @param data     需要返回的数据
     */
    public static void outputSuccessInfo(HttpServletResponse response, Object data) {
        send(response, GlobalResponse.success(data));
    }

    /**
     * 将信息发送给前台
     *
     * @param response       response
     * @param globalResponse 响应数据
     */
    private static void send(HttpServletResponse response, GlobalResponse<?> globalResponse) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            ServletUtil.write(response,
                              OBJECT_MAPPER.writeValueAsString(globalResponse),
                              MediaType.APPLICATION_JSON_VALUE);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json序列化异常！！！");
        }
    }
}
