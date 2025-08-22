package com.shiyueoe.springdemo.bean;

import com.shiyueoe.springdemo.bean.init.DisposableBean;
import com.shiyueoe.springdemo.bean.init.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestBean implements InitializingBean, DisposableBean {

    private String name;

    private String age;

    private TestRepositoryBean testRepositoryBean;

    public TestBean(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("[注解]：TestBean PostConstruct函数执行...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("[注解]：TestBean PreDestroy函数执行...");
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", testRepositoryBean=" + testRepositoryBean +
                '}';
    }

    public void setAge(String age) {
        //通过构造器注入属性必须得有set方法
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public TestRepositoryBean getTestRepositoryBean() {
        return testRepositoryBean;
    }

    public void setTestRepositoryBean(TestRepositoryBean testRepositoryBean) {
        this.testRepositoryBean = testRepositoryBean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[接口]：afterPropertiesSet执行中.....");
    }

    @Override
    public void destroy() throws Exception {
        //Spring约定大于配置，优先执行接口的回调方法，再执行注解的销毁方法
        System.out.println("[接口]：destroyBean执行中.....");
    }
}