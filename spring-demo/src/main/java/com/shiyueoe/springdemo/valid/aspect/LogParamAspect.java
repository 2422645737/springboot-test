package com.shiyueoe.springdemo.valid.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.shiyueoe.springdemo.valid.service.IServiceA;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class LogParamAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 医嘱操作事件总线
     */
    @Resource
    private EventBus orderLogEventBus;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Before("@annotation(logParam)")
    public void logRequestParams(JoinPoint joinPoint, LogParam logParam) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        try {
            orderLogEventBus.register(this);
            taskExecutor.execute(() -> {});
            String params = objectMapper.writeValueAsString(args);
            System.out.println("【注解日志】方法: " + methodName +
                    "，描述: " + logParam.value() +
                    "，入参: " + params);
        } catch (Exception e) {
            System.out.println("【注解日志】方法: " + methodName + "，入参序列化失败");
        }
    }
}