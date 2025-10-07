package com.shiyueoe.springdemo.bean;

import com.shiyueoe.springdemo.bean.aop.Advisor;
import com.shiyueoe.springdemo.bean.aop.AnnotationPointcut;
import com.shiyueoe.springdemo.bean.aop.LogExecution;
import com.shiyueoe.springdemo.bean.aop.LogExecutionInterceptor;
import com.shiyueoe.springdemo.bean.processor.AopProxyPostProcessor;
import com.shiyueoe.springdemo.bean.processor.LogBeanProcessor;
import com.shiyueoe.springdemo.bean.service.AdminServiceImpl;
import com.shiyueoe.springdemo.bean.service.UserService;
import com.shiyueoe.springdemo.bean.service.UserServiceImpl;

import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {

        //完全通过构造函数构造
//        SimpleBeanFactory factory = new SimpleBeanFactory();

        //通过构造函数构造，并注入属性
        SimpleBeanWithPropertyFactory factory = new SimpleBeanWithPropertyFactory();
        factory.addProcessor(new LogBeanProcessor());
        factory.addProcessor(new AopProxyPostProcessor(new Advisor(new AnnotationPointcut(LogExecution.class), new LogExecutionInterceptor())));

//        BeanDefinition beanDefinition2 = new BeanDefinition(TestRepositoryBean.class);
//        beanDefinition2.setAutoWiredConstructor(true);
//        beanDefinition2.setSingleton(true);
//        beanDefinition2.getPropertyValues().put("id",9527L);
//        factory.doCreateBean("testRepositoryBean",beanDefinition2);

//        BeanDefinition beanDefinition = new BeanDefinition(TestBean.class);
//        beanDefinition.setAutoWiredConstructor(true);
//        beanDefinition.setSingleton(true);
//        beanDefinition.getPropertyValues().put("age", "25岁");
//        beanDefinition.getPropertyValues().put("testRepositoryBean", null);
//
//
//        Object bean = factory.doCreateBean("testBean", beanDefinition, "wanghui");
//
//        TestBean b = (TestBean)bean;
//        System.out.println(b);
        BeanDefinition beanDefinition = new BeanDefinition(UserServiceImpl.class);
        beanDefinition.setAutoWiredConstructor(true);
        beanDefinition.setSingleton(true);
        beanDefinition.setBeanName("userServiceImpl");
        beanDefinition.getPropertyValues().put("adminService", null);

        BeanDefinition beanDefinition1 = new BeanDefinition(AdminServiceImpl.class);
        beanDefinition1.setAutoWiredConstructor(true);
        beanDefinition1.setSingleton(true);
        beanDefinition1.setBeanName("adminServiceImpl");
        beanDefinition1.getPropertyValues().put("userService", null);

        factory.addBeanDefinition(UserServiceImpl.class, beanDefinition);
        factory.addBeanDefinition(AdminServiceImpl.class, beanDefinition1);

        Object bean = factory.doCreateBean("userService", beanDefinition);
        Object bean1 = factory.doCreateBean("adminService", beanDefinition1);

        factory.destroySingletons();
    }
}