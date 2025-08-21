package com.shiyueoe.springdemo.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SimpleBeanFactory {
    private final Map<String, Object> singletonObjects = new HashMap<>();


    public Object doCreateBean(String beanName, BeanDefinition mbd, Object... args) throws Exception {
        Object bean = singletonObjects.get(beanName);

        if (bean != null) {
            return bean;
        }

        Class<?> beanClass = mbd.getBeanClass();

        Constructor<?>[] ctors = determineConstructors(beanClass, mbd);

        if (ctors != null || mbd.hasConstructorArgs() || args.length > 0) {
            bean = autowiredConstructor(beanClass, ctors, args, mbd);
        }else if(mbd.getPreferredConstructors() != null){
            bean = autowiredConstructor(beanClass, mbd.getPreferredConstructors(), null, mbd);
        }else {
            bean = beanClass.newInstance();
        }

        if(mbd.isSingleton()){
            singletonObjects.put(beanName, bean);
        }

        if(!mbd.getPropertyValues().isEmpty()){
            //属性注入
            populateBean(bean,mbd);
        }

        return bean;
    }

    private void populateBean(Object bean, BeanDefinition mbd) throws Exception {
        for (Map.Entry<String, Object> stringObjectEntry : mbd.getPropertyValues().entrySet()) {
            String propertyName = stringObjectEntry.getKey();
            Object propertyValue = stringObjectEntry.getValue();
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName,bean.getClass());
            propertyDescriptor.getWriteMethod().invoke(bean,propertyValue);
        }
    }

    private Object autowiredConstructor(Class<?> beanClass, Constructor<?>[] ctors, Object[] args, BeanDefinition mbd) throws Exception {
        if(ctors == null || ctors.length == 0){
            return beanClass.getDeclaredConstructor().newInstance();
        }

        Constructor<?> ctor = ctors[0];

        if(args != null && args.length > 0){
            return ctor.newInstance(args);
        }

        Class<?>[] parameterTypes = ctor.getParameterTypes();

        Object[] paramValues = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            paramValues[i] = null;
        }
        return ctor.newInstance(paramValues);
    }

    private Constructor<?>[] determineConstructors(Class<?> beanClass, BeanDefinition mbd) {
        if (mbd.isAutoWiredConstructor()) {
            return new Constructor[]{beanClass.getConstructors()[0]};
        }
        return null;
    }
}