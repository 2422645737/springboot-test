package com.shiyueoe.springdemo.bean;

import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {

        //完全通过构造函数构造
//        SimpleBeanFactory factory = new SimpleBeanFactory();

        //通过构造函数构造，并注入属性
        SimpleBeanWithPropertyFactory factory = new SimpleBeanWithPropertyFactory();

        BeanDefinition beanDefinition2 = new BeanDefinition(TestRepositoryBean.class);
        beanDefinition2.setAutoWiredConstructor(true);
        beanDefinition2.setSingleton(true);
        beanDefinition2.getPropertyValues().put("id",9527L);
        factory.doCreateBean("testRepositoryBean",beanDefinition2);

        BeanDefinition beanDefinition = new BeanDefinition(TestBean.class);
        beanDefinition.setAutoWiredConstructor(true);
        beanDefinition.setSingleton(true);
        beanDefinition.getPropertyValues().put("age", "25岁");


        Object bean = factory.doCreateBean("testBean", beanDefinition, "wanghui");

        TestBean b = (TestBean)bean;
        System.out.println(b);

    }
}