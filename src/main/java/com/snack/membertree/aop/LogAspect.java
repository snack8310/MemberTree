package com.snack.membertree.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("execution (* com.snack.membertree.service.*.*(..))")
    public void logPointcut() {

    }

    @Around("logPointcut()")
    public void around(JoinPoint point) {
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        Class<?>[] argTypes = new Class[point.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = point.getTarget().getClass().getMethod(methodName, argTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) point).proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            log.info("the method [{}] invoke takes {}ms", method.getName(), (end - start));
        }
    }
}
