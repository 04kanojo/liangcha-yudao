package com.liangcha.framework.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.auth2.pojo.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.liangcha.framework.redis.RedisKeyConstants.OAUTH2_ACCESS_TOKEN;
import static com.liangcha.framework.redis.RedisKeyConstants.OAUTH2_REFRESH_ACCESS_TOKEN;

@Component
public class RedisConfig {
    @Resource
    private CacheManager cacheManager;

    @Resource
    private SecurityProperties properties;

    /**
     * accessToken缓存
     */
    private Cache<String, LoginUser> tokenCache;

    /**
     * accessToken缓存
     */
    private Cache<String, LoginUser> refreshTokenCache;

    @PostConstruct
    public void init() {
        QuickConfig tokenQc = QuickConfig.newBuilder(OAUTH2_ACCESS_TOKEN)
                .expire(properties.getTokenExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        QuickConfig refreshTokenQc = QuickConfig.newBuilder(OAUTH2_REFRESH_ACCESS_TOKEN)
                .expire(properties.getRefreshTokenExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        tokenCache = cacheManager.getOrCreateCache(tokenQc);
        refreshTokenCache = cacheManager.getOrCreateCache(refreshTokenQc);
    }

    @Bean("tokenCache")
    public Cache<String, LoginUser> getTokenCache() {
        return tokenCache;
    }

    @Bean("refreshTokenCache")
    public Cache<String, LoginUser> getRefreshToken() {
        return refreshTokenCache;
    }
}
