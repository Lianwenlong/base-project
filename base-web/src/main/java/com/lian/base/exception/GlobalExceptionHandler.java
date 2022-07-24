package com.lian.base.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.lian.base.common.exception.BaseException;
import com.lian.base.common.exception.ExtRuntimeException;
import com.lian.base.common.model.GlobalResponse;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 11:17
 */

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    /**
     * 拦截Exception类的异常
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(Exception e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        //不能将异常详细信息返回前端，为了安全需要重新包装
        ExtRuntimeException extRuntimeException = BaseException.UNKNOWN.runtimeException();
        return new ResponseEntity<>(GlobalResponse.fail(extRuntimeException.getCode(),
                                                        extRuntimeException.getMessage(),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.valueOf(extRuntimeException.getStatus()));
    }


    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(HttpClientErrorException e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        return new ResponseEntity<>(GlobalResponse.fail(e.getStatusText(),
                                                        ExceptionUtil.getSimpleMessage(rootCause),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.valueOf(e.getRawStatusCode()));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(HttpServerErrorException e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        return new ResponseEntity<>(GlobalResponse.fail(e.getStatusText(),
                                                        ExceptionUtil.getSimpleMessage(rootCause),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.valueOf(e.getRawStatusCode()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(NoHandlerFoundException e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        //不能将异常详细信息返回前端，为了安全需要重新包装
        ExtRuntimeException extRuntimeException = BaseException.HTTP_NOT_FOUND.runtimeException();
        return new ResponseEntity<>(GlobalResponse.fail(extRuntimeException.getCode(),
                                                        extRuntimeException.getMessage(),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.valueOf(extRuntimeException.getStatus()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(ConstraintViolationException e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        //不能将异常详细信息返回前端，为了安全需要重新包装
        ExtRuntimeException extRuntimeException = BaseException.PARAM_VALID_ERROR.runtimeException(e.getMessage());
        return new ResponseEntity<>(GlobalResponse.fail(extRuntimeException.getCode(),
                                                        extRuntimeException.getMessage(),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.valueOf(extRuntimeException.getStatus()));
    }


    /**
     * 拦截ExecutionException类的异常
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(ExecutionException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(ExecutionException e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        ExtRuntimeException extRuntimeException = BaseException.UNKNOWN.runtimeException();
        if (rootCause instanceof ExtRuntimeException) {
            extRuntimeException = (ExtRuntimeException) rootCause;
        }
        return new ResponseEntity<>(GlobalResponse.fail(extRuntimeException.getCode(),
                                                        extRuntimeException.getMessage(),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 拦截ExtRuntimeException类的异常
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(ExtRuntimeException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(ExtRuntimeException e) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        return new ResponseEntity<>(GlobalResponse.fail(e.getCode(),
                                                        e.getMessage(),
                                                        e.getErrorData(),
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.valueOf(e.getStatus()));
    }


    /**
     * 参数校验异常统一返回
     *
     * @param exception 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(MethodArgumentNotValidException exception) {
        Throwable rootCause = ExceptionUtil.getRootCause(exception);
        log.error(ExceptionUtil.getMessage(rootCause), rootCause);
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        JSONArray builder = new JSONArray();
        for (FieldError error : fieldErrors) {
            JSONObject jsonObject = new JSONObject();
            //目标类
            Class<?> target = ClassUtil.getClass(result.getTarget());
            //错误源属性
            String sourceField = error.getField();
            //切割错误字段
            List<String> errorFields = StrUtil.split(sourceField, '.');
            //字段类型
            Class<?> fieldType = null;
            //目标字段
            Field targetField = null;
            for (int i = 0; i < errorFields.size(); i++) {
                String errorField = errorFields.get(i);
                //获取到对应的字段
                Field field;
                if (i == 0) {
                    field = ClassUtil.getDeclaredField(target, errorField);
                } else {
                    field = ClassUtil.getDeclaredField(fieldType, errorField);
                }
                if (ObjectUtil.isNull(field)) {
                    break;
                }
                //最后一次
                if (i == (errorFields.size() - 1)) {
                    targetField = field;
                }
                //获取到属性的类型
                fieldType = field.getType();
            }
            ApiModelProperty apiModelProperty = null;
            if (targetField != null) {
                apiModelProperty = AnnotationUtils.getAnnotation(targetField, ApiModelProperty.class);
            }

            if (apiModelProperty != null) {
                jsonObject.set(apiModelProperty.value(), error.getDefaultMessage());
            } else {
                jsonObject.set(error.getField(), error.getDefaultMessage());
            }
            builder.add(jsonObject);
        }
        return new ResponseEntity<>(GlobalResponse.fail("code.undefined",
                                                        builder,
                                                        (String) httpServletRequest.getAttribute(GlobalResponse.LOG_MARK_NAME)),
                                    HttpStatus.BAD_REQUEST);
    }
}
