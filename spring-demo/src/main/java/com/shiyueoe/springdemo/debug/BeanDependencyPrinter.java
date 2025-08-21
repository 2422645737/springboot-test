package com.shiyueoe.springdemo.debug;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanDependencyPrinter {
    private final ApplicationContext ctx;

    public BeanDependencyPrinter(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printBeans() {
        ConfigurableApplicationContext cac = (ConfigurableApplicationContext) ctx;
        for (String beanName : ctx.getBeanDefinitionNames()) {
            BeanDefinition bd = cac.getBeanFactory().getBeanDefinition(beanName);
            String[] dependsOn = bd.getDependsOn();
            if (dependsOn != null && dependsOn.length > 0) {
                System.out.println(beanName + " -> " + Arrays.toString(dependsOn));
            }
        }
    }
}