package com.shiyueoe.springdemo.valid.service;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ServiceC implements IServiceC{

    @Resource
    IServiceA serviceA;


    @Override
    @Transactional
    public void say() {
        serviceA.say("hello");
    }
}