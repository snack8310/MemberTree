package com.snack.membertree.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("execution (* com.snack.membertree.service.*.*(..))")
    public void logPointcut() {

    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Object object = null;
        try {
            object = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            String methodName = point.getSignature().getName();
            long end = System.currentTimeMillis();
            log.info("the method [{}] invoke takes {}ms", methodName, (end - start));
            return object;
        }
     }
}
