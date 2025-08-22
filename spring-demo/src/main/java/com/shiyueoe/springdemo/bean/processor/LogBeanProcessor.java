package com.shiyueoe.springdemo.bean.processor;

public class LogBeanProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        System.out.println("bean初始化，当前处理bean-[ " + beanName + " ]...前置函数日志打印");
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        System.out.println("bean初始化，当前处理bean-[ " + beanName + " ]...后置函数日志打印");
        return bean;
    }
}