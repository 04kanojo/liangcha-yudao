package com.liangcha.system.sms.service;

import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import com.liangcha.server.controller.auth.vo.AuthSmsSendReqVO;

/**
 * 短信验证码 Service 接口
 *
 * @author 凉茶
 */
public interface SmsCodeService {

    /**
     * 创建短信验证码，并进行发送
     */
    void sendSmsCode(AuthSmsSendReqVO reqVO);


    /**
     * 执行真正的短信发送
     *
     * @param message 短信
     */
    void doSendSms(SmsSendMessage message);

}
