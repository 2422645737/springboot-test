package com.shiyueoe.springdemo.valid.service;


import com.shiyueoe.springdemo.valid.aspect.LogParam;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceB implements IServiceB{

    @Resource
    IServiceC serviceC;


    @Override
    @LogParam("创建用户接口")
    public void say() {
        serviceC.say();
    }
}