package com.shiyueoe.springdemo.bean.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationPointcut {
    private final Class<? extends Annotation> annotationType;

    public AnnotationPointcut(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
    }

    public boolean matches(Method method,Class<?> targetClass) throws NoSuchMethodException {
        Method implMethod = targetClass.getMethod(method.getName(), method.getParameterTypes());
        return implMethod.isAnnotationPresent(annotationType);
    }
}