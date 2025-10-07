package com.shiyueoe.springdemo.valid.service;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceC implements IServiceC{

    @Resource
    IServiceA serviceA;


    @Override
    public void say() {
        serviceA.say("hello");
    }
}