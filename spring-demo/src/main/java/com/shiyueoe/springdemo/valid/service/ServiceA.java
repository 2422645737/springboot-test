package com.shiyueoe.springdemo.valid.service;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class ServiceA implements IServiceA{

    @Resource
    IServiceB serviceB;


    /**
     * 医嘱操作事件总线
     */
    @Resource
    private EventBus orderLogEventBus;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        orderLogEventBus.register(this);
    }
    @Override
    @Subscribe
    public void say(String s) {
        serviceB.say();
    }
}