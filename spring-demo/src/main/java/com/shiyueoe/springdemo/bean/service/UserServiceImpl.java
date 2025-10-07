package com.shiyueoe.springdemo.bean.service;

import com.shiyueoe.springdemo.bean.aop.LogExecution;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @fileName: UserServiceImpl
 * @author: wanghui
 * @createAt: 2025/08/22 09:36:37
 * @updateBy:
 * @copyright:
 */

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private AdminService adminService;

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @LogExecution
    public String getUserById(Long id) {
        System.out.println("getUserById执行中....");
        return "hello world";
    }
}