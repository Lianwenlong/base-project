package com.lian.base.exception;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.expression.engine.spel.SpELEngine;

import com.lian.base.common.annotation.exception.PackThrows;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 异常包装切面
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 10:33
 */
@Aspect
@Component
@Slf4j
public class ExceptionPackAspect {


    /**
     * 根据注解确定是否自动记录日志
     */
    @Pointcut("@annotation(com.lian.base.common.annotation.exception.PackThrows)")
    public void annotationAspect() {
    }

    @Around("annotationAspect()")
    public Object methodLogHandler(ProceedingJoinPoint process) throws Throwable {
        //获取连接点的方法签名对象
        MethodSignature methodSignature = (MethodSignature) process.getSignature();
        //获取执行的方法
        Method method = methodSignature.getMethod();
        //获取执行的类
        Class<?> clazz = method.getDeclaringClass();
        //获取返回值类型
        Class<?> returnType = method.getReturnType();
        //获取方法上的PackThrows注解
        PackThrows packThrows = AnnotationUtils.getAnnotation(method, PackThrows.class);
        if (ObjectUtil.isNull(packThrows)) {
            //因为以PackThrows注解为切入点，因此该判断永远不会进入
            return process.proceed();
        }

        try {
            return process.proceed();
        } catch (Throwable throwable) {
            Class<? extends Throwable>[] value = packThrows.value();
            Class<? extends Throwable>[] filter = ArrayUtil.filter(value,
                                                                   aClass -> ClassUtil.isAssignable(throwable.getClass(),
                                                                                                    aClass));
            if (ObjectUtil.isEmpty(filter)) {
                throw throwable;
            }
            SpELEngine spELEngine = new SpELEngine();
            Map<String, Object> context = new HashMap<>(1);
            context.put("currentException", throwable);
            spELEngine.eval(packThrows.wrap(), context);
        }
        return null;
    }
}
