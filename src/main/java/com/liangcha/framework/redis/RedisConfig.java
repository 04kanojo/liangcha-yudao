package com.liangcha.framework.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.domain.OAuth2RefreshTokenDO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;

import static com.liangcha.framework.common.enums.RedisKeyConstants.OAUTH2_ACCESS_TOKEN;
import static com.liangcha.framework.common.enums.RedisKeyConstants.OAUTH2_REFRESH_ACCESS_TOKEN;

@Component
public class RedisConfig {
    @Resource
    private CacheManager cacheManager;

    /**
     * accessToken缓存
     */
    private Cache<String, OAuth2AccessTokenDO> tokenCache;

    /**
     * accessToken缓存
     */
    private Cache<String, OAuth2RefreshTokenDO> refreshTokenCache;

    @PostConstruct
    public void init() {
        QuickConfig tokenQc = QuickConfig.newBuilder(OAUTH2_ACCESS_TOKEN)
                .expire(Duration.ofMinutes(20))
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        QuickConfig refreshTokenQc = QuickConfig.newBuilder(OAUTH2_REFRESH_ACCESS_TOKEN)
                .expire(Duration.ofMinutes(60))
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        tokenCache = cacheManager.getOrCreateCache(tokenQc);
        refreshTokenCache = cacheManager.getOrCreateCache(refreshTokenQc);
    }

    @Bean
    public Cache<String, OAuth2AccessTokenDO> getTokenCache() {
        return tokenCache;
    }

    @Bean
    public Cache<String, OAuth2RefreshTokenDO> getRefreshToken() {
        return refreshTokenCache;
    }
}
