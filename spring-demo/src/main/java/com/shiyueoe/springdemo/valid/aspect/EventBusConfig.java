package com.shiyueoe.springdemo.valid.aspect;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:事件总线配置类
 * @fileName: EventBusConfig
 * @author: TongBo
 * @createAt: 2021/7/26 15:32
 * @updateBy: TongBo
 * @remark: 众阳健康
 */
@Configuration
public class EventBusConfig {
    
    /**
     * 日志写入事件总线
     * @return 事件总线
     */
    @Bean
    public EventBus orderLogEventBus() {
        return new EventBus();
    }
}