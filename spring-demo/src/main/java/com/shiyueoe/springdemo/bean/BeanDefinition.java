package com.shiyueoe.springdemo.bean;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BeanDefinition {

    private Class<?> beanClass;

    private boolean singleton;

    private Map<String,Object> propertyValues = new HashMap<>();

    private boolean autoWiredConstructor;

    private Constructor<?>[] preferredConstructors;
    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public Map<String, Object> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, Object> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public boolean isAutoWiredConstructor() {
        return autoWiredConstructor;
    }

    public void setAutoWiredConstructor(boolean autoWiredConstructor) {
        this.autoWiredConstructor = autoWiredConstructor;
    }

    public Constructor<?>[] getPreferredConstructors() {
        return preferredConstructors;
    }

    public void setPreferredConstructors(Constructor<?>[] preferredConstructors) {
        this.preferredConstructors = preferredConstructors;
    }

    public boolean hasConstructorArgs() { return !propertyValues.isEmpty(); }
}