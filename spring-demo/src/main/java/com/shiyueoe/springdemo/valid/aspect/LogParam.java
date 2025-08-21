package com.shiyueoe.springdemo.valid.aspect;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)        // 作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时可用
@Documented
public @interface LogParam {
    String value() default "";
}