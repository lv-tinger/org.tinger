package org.tinger.spring.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheExecutor {
    @Around("@annotation(org.tinger.data.cacheable.CacheMethod)")
    public Object read(ProceedingJoinPoint proceedingJoinPoint) {
        Object value = null;
        value = "hello world";
        try {
            if (value == null) {
                value = proceedingJoinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }
}