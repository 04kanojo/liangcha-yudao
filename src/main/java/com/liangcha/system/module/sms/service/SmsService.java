package com.liangcha.system.module.sms.service;

import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import com.liangcha.system.module.sms.dto.SmsCodeSendReqDTO;

/**
 * 短信验证码 Service 接口
 *
 * @author 凉茶
 */
public interface SmsService {

    /**
     * 创建短信验证码，并进行发送
     */
    void sendSmsCode(SmsCodeSendReqDTO reqVO);


    /**
     * 执行真正的短信发送
     * 默认腾讯云，暂时是硬编码，如果有其他平台需求，请自行编码
     *
     * @param message 短信
     */
    void doSendSms(SmsSendMessage message);

    /**
     * 使用验证码
     *
     * @param code 验证码
     */
    void useSmsCode(String code);

}
