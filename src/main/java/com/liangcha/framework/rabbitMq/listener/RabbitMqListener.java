package com.liangcha.framework.rabbitMq.listener;

import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import com.liangcha.system.sms.service.SmsCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息队列监听器
 *
 * @author 凉茶
 */
@Slf4j
@Component
public class RabbitMqListener {

    @Resource
    private SmsCodeService smsSendService;

    @RabbitListener(queues = "sms")
    public void listenSmsQueue(SmsSendMessage message) {
        log.info("[Message][消息内容({})]", message);
        smsSendService.doSendSms(message);
    }

}
