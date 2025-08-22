package com.shiyueoe.springdemo.bean.aop;


import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;

public class Advisor {
    private final AnnotationPointcut pointcut;
    private final MethodInterceptor interceptor;

    public Advisor(AnnotationPointcut pointcut, MethodInterceptor interceptor) {
        this.pointcut = pointcut;
        this.interceptor = interceptor;
    }

    public boolean matches(Method method,Class<?> targetClass) throws NoSuchMethodException {
        return pointcut.matches(method,targetClass);
    }

    public MethodInterceptor getInterceptor() {
        return interceptor;
    }
}