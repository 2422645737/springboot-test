package com.shiyueoe.springdemo.bean.processor;

import com.shiyueoe.springdemo.bean.aop.Advisor;
import com.shiyueoe.springdemo.bean.aop.SimpleMethodInvocation;

import java.lang.reflect.Proxy;

public class AopProxyPostProcessor implements BeanPostProcessor{

    private Advisor advisor;

    public AopProxyPostProcessor(Advisor advisor) {
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Class<?> beanClass = bean.getClass();

        if(beanClass.getInterfaces().length == 0){
            //只有继承接口的类才使用JDK动态代理
            return bean;
        }

        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                beanClass.getInterfaces(),
                (proxy,method,args) -> {
                    if(advisor.matches(method,bean.getClass())){
                        return advisor.getInterceptor().invoke(new SimpleMethodInvocation(bean,method,args));
                    }
                    return method.invoke(bean,args);
        });
    }
}