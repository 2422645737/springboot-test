package com.shiyueoe.springdemo.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SimpleBeanWithPropertyFactory {
    private final Map<String, Object> singletonObjects = new HashMap<>();


    public Object doCreateBean(String beanName, BeanDefinition mbd, Object... args) throws Exception {
        Object bean = singletonObjects.get(beanName);

        if (bean != null) {
            return bean;
        }

        Class<?> beanClass = mbd.getBeanClass();

        //选择合适的构造器
        Constructor<?>[] ctors = determineConstructors(beanClass, mbd);

        //通过构造器创建对象
        if (ctors != null || mbd.hasConstructorArgs() || args.length > 0) {
            bean = autowiredConstructor(beanClass, ctors, args);
        }else if(mbd.getPreferredConstructors() != null){
            bean = autowiredConstructor(beanClass, mbd.getPreferredConstructors(), null);
        }else {
            bean = beanClass.newInstance();
        }

        //将对象放入单例池中
        if(mbd.isSingleton()){
            singletonObjects.put(beanName, bean);
        }

        //属性注入
        if(!mbd.getPropertyValues().isEmpty()){
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

    /**
     * 通过构造器创建bean
     * @param beanClass
     * @param ctors
     * @param args
     * @return {@link Object }
     */

    private Object autowiredConstructor(Class<?> beanClass, Constructor<?>[] ctors, Object[] args) throws Exception {
        if(ctors == null || ctors.length == 0){
            return beanClass.getDeclaredConstructor().newInstance();
        }

        Constructor<?> ctor = ctors[0];


        Class<?>[] parameterTypes = ctor.getParameterTypes();

        Object[] paramValues = new Object[parameterTypes.length];

        //如果构造函数的参数是bean，则从单例池中寻找可用的bean,若没找到则设置为null
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];

            Object bean = getBeanByType(parameterType);

            if(bean != null){
                paramValues[i] = bean;
            }else if(args != null && i < args.length){
                paramValues[i] = args[i];
            }else{
                paramValues[i] = null;
            }
        }

        return ctor.newInstance(paramValues);
    }

    private Object getBeanByType(Class<?> parameterType) {
        for (Object bean : singletonObjects.values()) {
            if(parameterType.isAssignableFrom(bean.getClass())){
                return bean;
            }
        }
        return null;
    }

    private Constructor<?>[] determineConstructors(Class<?> beanClass, BeanDefinition mbd) {
        if (mbd.isAutoWiredConstructor()) {
            return new Constructor[]{beanClass.getConstructors()[0]};
        }
        return null;
    }
}