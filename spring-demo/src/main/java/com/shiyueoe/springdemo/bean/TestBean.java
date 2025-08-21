package com.shiyueoe.springdemo.bean;

import org.springframework.stereotype.Component;

public class TestBean {

    private String name;

    private String age;

    private TestRepositoryBean testRepositoryBean;

    public TestBean(String name) {
        this.name = name;
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
}