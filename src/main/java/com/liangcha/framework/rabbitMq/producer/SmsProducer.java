package com.liangcha.framework.rabbitMq.producer;

import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Sms 短信相关消息的 Producer
 *
 * @author 凉茶
 */
@Slf4j
@Component
public class SmsProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送 {@link SmsSendMessage} 消息
     *
     * @param mobile        手机号
     * @param apiTemplateId 短信模板编号
     */
    public void sendSmsSendMessage(String mobile, String apiTemplateId, String code) {
        SmsSendMessage message = new SmsSendMessage().setMobile(mobile).setApiTemplateId(apiTemplateId).setCode(code);
        String queueName = "sms";
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
