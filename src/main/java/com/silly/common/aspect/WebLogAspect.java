package com.silly.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silly.common.annotation.Log;
import com.silly.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 *  日志切面 打印请求及响应日志
 * @Author: wei.wang7
 * @Date: 2020/8/30 15:34
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    /**
     * 切点
     */
    @Pointcut("@within(com.silly.common.annotation.Log)")
    public void pointCut() {
    }

    /**
     * 方法请求前接入
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        try {
            if (isSkip(joinPoint)) {
                return;
            }
            // 开始打印请求日志
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 打印请求相关参数
            // 打印请求 url
            log.info("URL        : {}", request.getRequestURL().toString());
            // 打印 Http method
            log.info("HTTP Method: {}", request.getMethod());
            // 打印调用 controller 的全路径以及执行方法
            log.info("Class Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            // 打印请求的 IP
            log.info("IP          : {}", request.getRemoteAddr());
            // 打印请求入参
            log.info("Request Args: {}", request.getQueryString());

        } catch (Exception ex) {
            log.error("WebLogAspect->before", ex);
        }
    }

    /**
     * 目标方法正常完成后切入
     *
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "pointCut()")
    public void afterReturning(JoinPoint joinPoint, BaseResponse result) {
        try {
            if (isSkip(joinPoint)) {
                return;
            }
            String response = null;
            if (result != null) {
                response = new ObjectMapper().writeValueAsString(result);
            }
            log.info("Response    : {}", response);
            log.info(System.lineSeparator());
        } catch (Exception ex) {
            log.error("afterReturning->before", ex);
        }
    }

    private boolean isSkip(JoinPoint joinPoint) throws NoSuchMethodException {
        Class<?> clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        Method method = clazz.getMethod(methodName, parameterTypes);
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation == null) {
            //获取类上注解
            logAnnotation = clazz.getAnnotation(Log.class);
        }
        return logAnnotation.skip();
    }
}
