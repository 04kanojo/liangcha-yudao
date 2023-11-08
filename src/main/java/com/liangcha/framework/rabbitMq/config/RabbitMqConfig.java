package com.liangcha.framework.rabbitMq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 凉茶
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 短信队列
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("sms");
    }

}
