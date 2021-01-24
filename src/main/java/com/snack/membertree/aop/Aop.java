package com.snack.membertree.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
//@Component
public class Aop implements InvocationHandler {

    private Object target;

    public Aop(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        //调用真正的方法
        Object retVal = method.invoke(target, args);
        long end = System.currentTimeMillis();
        log.info("the method [{}] invoke takes {}ms", method.getName(), (end - start));
        return retVal;
    }

}
