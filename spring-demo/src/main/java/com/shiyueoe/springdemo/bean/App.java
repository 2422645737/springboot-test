package com.shiyueoe.springdemo.bean;

public class App {
    public static void main(String[] args) throws Exception {
        SimpleBeanFactory factory = new SimpleBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(TestBean.class);
        beanDefinition.setAutoWiredConstructor(true);
        Object bean = factory.doCreateBean("testBean", beanDefinition, "wanghui", "30岁");

        TestBean b = (TestBean)bean;
        System.out.println(b);

    }
}