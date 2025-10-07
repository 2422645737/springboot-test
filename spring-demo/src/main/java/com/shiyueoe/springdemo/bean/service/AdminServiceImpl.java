package com.shiyueoe.springdemo.bean.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    private UserService userService;

    public UserService getUserService() {

        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doSomething() {


    }
}