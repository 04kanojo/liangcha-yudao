package com.liangcha.framework.rabbitMq.producer;

import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

import static com.liangcha.framework.rabbitMq.enums.RabbitQueueConstants.SMS;

/**
 * Sms 短信相关消息的 Producer
 *
 * @author 凉茶
 */
@Component
public class SmsProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param mobile        手机号
     * @param apiTemplateId 短信模板编号
     */
    public void sendSmsSendMessage(String mobile, String apiTemplateId, LinkedHashMap<String, String> params, Long logId) {
        SmsSendMessage message = new SmsSendMessage()
                .setMobile(mobile)
                .setParamMap(params)
                .setLogId(logId)
                .setApiTemplateId(apiTemplateId);

        rabbitTemplate.convertAndSend(SMS, message);
    }

}
