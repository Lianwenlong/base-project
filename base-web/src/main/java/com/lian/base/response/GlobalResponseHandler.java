package com.lian.base.response;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;
import com.lian.base.common.exception.ExtRuntimeException;
import com.lian.base.common.model.GlobalResponse;
import com.lian.base.config.BaseProperties;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 * 全局返回值处理
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 10:48
 */


@RequiredArgsConstructor
@ControllerAdvice(annotations = {RestController.class, Controller.class, ControllerAdvice.class})
@ConditionalOnProperty(prefix = "base.global-response", name = "enabled", havingValue = "true")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private final BaseProperties baseProperties;

    /**
     * 忽略路径列表,支持通配
     * 默认忽略swagger2相关包路径
     */
    private static final List<String> IGNORE_PATH_LIST = Stream.of("springfox.documentation.*.web.*")
                                                               .collect(Collectors.toList());


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        BaseProperties.GlobalResponse globalResponse = baseProperties.getGlobalResponse();
        if (ObjectUtil.isEmpty(globalResponse)) {
            //默认不处理
            return false;
        }
        //禁用返回值过滤
        if (!globalResponse.isEnabled()) {
            return false;
        }
        //执行目标类
        final Class<?> targetClazz = returnType.getDeclaringClass();
        //执行目标方法
        Method targetMethod = returnType.getMethod();
        if (ObjectUtil.isNull(targetMethod)) {
            return false;
        }

        //完整方法名
        final String methodGenericName = targetMethod.toGenericString();
        String[] ignoreMethod = globalResponse.getIgnoreMethod();
        if (ArrayUtil.contains(ignoreMethod, methodGenericName)) {
            return false;
        }

        //忽略返回值
        final Class<?> returnTypeClass = returnType.getParameterType();
        Class<?>[] ignoreReturnType = globalResponse.getIgnoreReturnType();
        Class<?> match = ArrayUtil.firstMatch(o -> ClassUtil.isAssignable(o, returnTypeClass), ignoreReturnType);
        if (ObjectUtil.isNotNull(match)) {
            return false;
        }
        //表示是否跳过返回值包装
        AtomicBoolean isSkip = new AtomicBoolean(false);
        //获取配置的忽略路径
        String[] ignorePath = globalResponse.getIgnorePath();
        //将配置的忽略路径合并
        if (ObjectUtil.isNotEmpty(ignorePath)) {
            Collections.addAll(IGNORE_PATH_LIST, ignorePath);
        }
        //忽略掉swagger2类
        IGNORE_PATH_LIST.parallelStream().forEach(path -> {
            if (ReUtil.isMatch(path, targetClazz.getName())) {
                isSkip.set(true);
            }
        });

        if (isSkip.get()) {
            //忽略返回值包装
            return false;
        }

        return GlobalResponse.class != returnTypeClass;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        Class<?> parameterType = returnType.getParameterType();

        //返回类型不包含json格式和文本格式
        if (!selectedContentType.includes(MediaType.APPLICATION_JSON) && !selectedContentType.includes(MediaType.TEXT_PLAIN)) {
            return body;
        }
        //设置统一返回编码
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        //返回值类型已经被包装
        if (body instanceof GlobalResponse) {
            return body;
        }

        //获取日志标识ID
        final String logMarkId = (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME);

        //返回值属于一个异常
        if (body instanceof Exception) {
            Exception exception = (Exception) body;
            if (exception instanceof ExtRuntimeException) {
                ExtRuntimeException ex = (ExtRuntimeException) exception;
                httpServletResponse.setStatus(ex.getStatus());
                return GlobalResponse.fail(ex.getCode(), ex.getMessage(), ex.getErrorData(), logMarkId);
            }
            return GlobalResponse.fail("code.undefined", exception.getMessage(), logMarkId);
        }
        //获取携带的异常信息
        Throwable throwable = (Throwable) httpServletRequest.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (ObjectUtil.isNotNull(throwable)) {
            Throwable rootCause = ExceptionUtil.getRootCause(throwable);
            if (rootCause instanceof ExtRuntimeException) {
                ExtRuntimeException ex = (ExtRuntimeException) rootCause;
                httpServletResponse.setStatus(ex.getStatus());
                return GlobalResponse.fail(ex.getCode(), ex.getMessage(), ex.getErrorData(), logMarkId);
            }
            return GlobalResponse.fail("code.undefined", rootCause.getMessage(), null, logMarkId);
        }

        //获取响应状态码
        int status = httpServletResponse.getStatus();
        if (HttpStatus.OK.value() != status) {
            return GlobalResponse.fail(String.valueOf(status), HttpStatus.resolve(status), body, logMarkId);
        }

        //无返回值
        if (parameterType == void.class) {
            return GlobalResponse.success(null, logMarkId);
        }

        if (parameterType != String.class) {
            return GlobalResponse.success(body, logMarkId);
        }
        //包装String返回值
        GlobalResponse<Object> globalResponse = GlobalResponse.success(body, logMarkId);
        //将string包装后转换
        return JSONUtil.toJsonStr(globalResponse);
    }
}
