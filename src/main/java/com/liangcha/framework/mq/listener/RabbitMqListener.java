package com.liangcha.framework.mq.listener;

import com.liangcha.framework.mq.message.SmsSendMessage;
import com.liangcha.system.service.sms.SmsCodeService;
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
