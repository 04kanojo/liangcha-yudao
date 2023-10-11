package com.liangcha.system.service.sms;

import com.liangcha.framework.mq.message.SmsSendMessage;
import com.liangcha.system.controller.auth.vo.AuthSmsSendReqVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码 Service 接口
 *
 * @author 凉茶
 */
public interface SmsCodeService {

    /**
     * 创建短信验证码，并进行发送
     */
    void sendSmsCode(AuthSmsSendReqVO reqVO, HttpServletRequest request);


    /**
     * 执行真正的短信发送
     *
     * @param message 短信
     */
    void doSendSms(SmsSendMessage message);

}
