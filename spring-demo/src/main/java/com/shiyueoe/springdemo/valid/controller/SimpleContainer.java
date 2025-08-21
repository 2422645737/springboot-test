package com.shiyueoe.springdemo.valid.controller;

import com.shiyueoe.springdemo.valid.service.ServiceA;

import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleContainer {
    /***
     * 用于存放完全初始化好的Bean，Bean处于可状态
     * 这个Map定义和Spring中一级缓存命名一致
     * */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /***
     * 用于存放刚创建出来的Bean，其属性还没有处理，因此存放在该缓存中的Bean还不可用。
     * 这个Map定义和Spring中三级缓存命名一致
     * */
    private final Map<String, Object> singletonFactories = new HashMap<>(16);

    public static void main(String[] args) {
        SimpleContainer container = new SimpleContainer();
        ServiceA componentA = container.getBean(ServiceA.class);
        componentA.say("s");
    }

    public <T> T getBean(Class<T> beanClass) {
        String beanName = this.getBeanName(beanClass);
        // 首先根据beanName从缓存中获取Bean实例
        Object bean = this.getSingleton(beanName);
        if (bean == null) {
            // 如果未获取到Bean实例，则创建Bean实例
            return createBean(beanClass, beanName);
        }
        return (T) bean;
    }
    /***
     * 从一级缓存和二级缓存中根据beanName来获取Bean实例，可能为空
     * */
    private Object getSingleton(String beanName) {
        // 首先尝试从一级缓存中获取
        Object instance = singletonObjects.get(beanName);
        if (instance == null) { // Spring 之所以能解决循环依赖问题，也是靠着这个singletonFactories
            instance = singletonFactories.get(beanName);
        }
        return instance;
    }

    /***
     * 创建指定Class的实例，返回完全状态的Bean(属性可用)
     *
     * */
    private <T> T createBean(Class<T> beanClass, String beanName) {
        try {
            Constructor<T> constructor = beanClass.getDeclaredConstructor();
            T instance = constructor.newInstance();
            // 先将刚创建好的实例存放到三级缓存中，如果没有这一步，Spring 也无法解决三级缓存
            singletonFactories.put(beanName, instance);
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                field.setAccessible(true);
                // 精髓是这里又调用了getBean方法，例如正在处理ComponentA.componentB属性，
                // 执行到这里时就会去实例化ComponentB。因为在getBean方法首先去查缓存，
                // 而一级缓存和三级缓存中没有ComponentB实例数据，所以又会调用到当前方法，
                // 而在处理ComponentB.componentA属性时，又去调用getBean方法去缓存中查找，
                // 因为在前面我们将ComponentA实例放入到了三级缓存，因此可以找到。
                // 所以ComponentB的实例化结束，方法出栈，返回到实例化ComponentA的方法栈中，
                // 这时ComponentB已经初始化完成，因此ComponentA.componentB属性赋值成功！
                field.set(instance, this.getBean(fieldType));
            }
            // 最后再将初始化好的Bean设置到一级缓存中。
            singletonObjects.put(beanName, instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    /**
     * 将类名小写作为beanName，Spring底层实现和这个差不多，也是使用javaBeans的
     * {@linkplain Introspector#decapitalize(String)}
     **/
    private String getBeanName(Class<?> clazz) {
        String clazzName = clazz.getName();
        int index = clazzName.lastIndexOf(".");
        String className = clazzName.substring(index);
        return Introspector.decapitalize(className);
    }
}