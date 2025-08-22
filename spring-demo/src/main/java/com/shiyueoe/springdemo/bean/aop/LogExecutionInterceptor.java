package com.shiyueoe.springdemo.bean.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogExecutionInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("aop动态代理打印日志，开始......");
        Object proceed = methodInvocation.proceed();
        System.out.println("aop动态代理打印日志，结束......");
        return proceed;
    }
}