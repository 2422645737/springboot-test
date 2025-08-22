package com.shiyueoe.springdemo.bean.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @description: 实现日志的动态代理模式
 * @fileName: LogExecution
 * @author: wanghui
 * @createAt: 2025/08/22 09:16:24
 * @updateBy:
 * @copyright:
 */

@Target(ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface LogExecution {

}