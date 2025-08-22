package com.shiyueoe.springdemo.bean;

import com.shiyueoe.springdemo.bean.init.DisposableBean;
import com.shiyueoe.springdemo.bean.init.InitializingBean;
import com.shiyueoe.springdemo.bean.processor.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeanWithPropertyFactory {
    /**
     * 单例池
     */
    private final Map<String, Object> singletonObjects = new HashMap<>();


    /**
     * bean自定义处理器
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * bean销毁回调
     */
    private final Map<String,Object> disposableBeans = new HashMap<>();

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


        //属性注入
        if(!mbd.getPropertyValues().isEmpty()){
            populateBean(bean,mbd);
        }

        bean = initializeBean(beanName, bean);

        //将对象放入单例池中，这里放置单例池的时机要靠后，要等before和after函数都执行完，再放入单例池中
        if(mbd.isSingleton()){
            singletonObjects.put(beanName, bean);
        }
        return bean;
    }

    private Object initializeBean(String beanName, Object bean) throws Exception {
        //执行postProcessor   before函数
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }

        //执行初始化回调,优先执行接口的初始化
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        //再调用@PostConstruct方法
        for (Method method : bean.getClass().getMethods()) {
            if(method.isAnnotationPresent(PostConstruct.class)){
                method.setAccessible(true);
                method.invoke(bean);
            }
        }

        //注册销毁方法
        registerDisposableBean(beanName, bean);

        //执行PostProcessor  after函数
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }

    private void populateBean(Object bean, BeanDefinition mbd) throws Exception {
        for (Map.Entry<String, Object> stringObjectEntry : mbd.getPropertyValues().entrySet()) {
            String propertyName = stringObjectEntry.getKey();
            Object propertyValue = stringObjectEntry.getValue();

            Class<?> propertyBeanType = bean.getClass().getDeclaredField(propertyName).getType();

            //如果没有外部传入属性值，则从beanFactory中获取
            if(propertyValue == null){
                Object propertyBean = getBeanByType(propertyBeanType);
                if(propertyBean != null){
                    propertyValue = propertyBean;
                }
            }

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

    public void addProcessor(BeanPostProcessor processor){
        this.beanPostProcessors.add(processor);
    }

    /**
     * 注册销毁方法
     * @param beanName
     * @param bean
     */

    private void registerDisposableBean(String beanName, Object bean){
        if(bean instanceof DisposableBean){
            disposableBeans.put(beanName, (DisposableBean)bean);
        }else {
            for (Method method : bean.getClass().getMethods()) {
                if(method.isAnnotationPresent(PreDestroy.class)){
                    disposableBeans.put(beanName, bean);
                    break;
                }
            }
        }
    }

    public void destroySingletons() throws Exception{
        for (Map.Entry<String, Object> entry : disposableBeans.entrySet()) {
            String beanName = entry.getKey();
            Object bean = entry.getValue();

            //spring优先执行接口的销毁方法
            if(bean instanceof DisposableBean){
                ((DisposableBean) bean).destroy();
            }

            //然后执行注解的销毁方法
            for (Method method : bean.getClass().getMethods()) {
                if(method.isAnnotationPresent(PreDestroy.class)){
                    method.setAccessible(true);
                    method.invoke(bean);
                }
            }
            System.out.println("bean销毁，销毁函数日志打印");
        }


    }
}