package com.shiyueoe.springdemo.bean.init;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}