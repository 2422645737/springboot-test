package com.shiyueoe.springdemo.bean;

import org.springframework.stereotype.Component;

public class TestBean {

    private String name;

    private String age;

    public TestBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestBean [name=" + name + ", age=" + age + "]";
    }


}