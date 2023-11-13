package com.liangcha.framework.rabbitMq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.liangcha.framework.rabbitMq.enums.RabbitQueueConstants.SMS;

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
        return new Queue(SMS);
    }

}
