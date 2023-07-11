package com.liangcha.framework.captcha.config;

import com.anji.captcha.service.CaptchaCacheService;
import com.liangcha.framework.captcha.service.RedisCaptchaServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@AutoConfiguration
public class YudaoCaptchaConfiguration {

    @Bean
    public CaptchaCacheService captchaCacheService(StringRedisTemplate stringRedisTemplate) {
        return new RedisCaptchaServiceImpl(stringRedisTemplate);
    }

}
