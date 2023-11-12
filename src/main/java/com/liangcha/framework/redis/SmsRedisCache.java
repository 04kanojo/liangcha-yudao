package com.liangcha.framework.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.system.sms.pojo.SmsCode;
import com.liangcha.system.sms.properties.SmsCodeProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.liangcha.framework.redis.RedisKeyConstants.SMS_CODE;


/**
 * 短信相关缓存
 *
 * @author 凉茶
 */
@Component
public class SmsRedisCache extends RedisCache {
    /**
     * 短信缓存
     * 格式：手机号+验证码对象
     */
    private Cache<String, SmsCode> smsCodeCache;

    @Resource
    private SmsCodeProperties properties;

    @PostConstruct
    public void init() {
        QuickConfig smsCodeQc = QuickConfig.newBuilder(SMS_CODE)
                .expire(properties.getExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        smsCodeCache = cacheManager.getOrCreateCache(smsCodeQc);
    }

    @Bean
    public Cache<String, SmsCode> getSmsCodeCache() {
        return smsCodeCache;
    }
}
