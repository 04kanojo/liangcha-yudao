package com.liangcha.framework.security.mq;

import com.liangcha.framework.common.mq.RedisMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OAuth 2.0 客户端相关消息的 Producer
 *
 * @author 凉茶
 */
@Component
public class OAuth2ClientProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;

    public void sendOAuth2ClientRefreshMessage() {
        OAuth2ClientRefreshMessage message = new OAuth2ClientRefreshMessage();
        redisMQTemplate.send(message);
    }

}
