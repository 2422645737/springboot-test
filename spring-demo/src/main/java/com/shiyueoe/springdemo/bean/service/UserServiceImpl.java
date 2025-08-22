package com.shiyueoe.springdemo.bean.service;

import com.shiyueoe.springdemo.bean.aop.LogExecution;

/**
 * @description:
 * @fileName: UserServiceImpl
 * @author: wanghui
 * @createAt: 2025/08/22 09:36:37
 * @updateBy:
 * @copyright:
 */

public class UserServiceImpl implements UserService{
    @Override
    @LogExecution
    public String getUserById(Long id) {
        System.out.println("getUserById执行中....");
        return "hello world";
    }
}