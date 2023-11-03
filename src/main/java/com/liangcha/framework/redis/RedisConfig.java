package com.liangcha.framework.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2Approve;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

import static com.liangcha.framework.redis.RedisKeyConstants.*;

@Component
public class RedisConfig {
    @Resource
    private CacheManager cacheManager;

    @Resource
    private SecurityProperties properties;

    /**
     * accessToken缓存
     * 格式：token+用户信息
     */
    private Cache<String, LoginUser> tokenCache;

    /**
     * refreshToken缓存
     * 格式：refreshToken+用户信息
     */
    private Cache<String, LoginUser> refreshTokenCache;

    /**
     * client缓存
     * 格式：客户端id+客户端信息
     */
    private Cache<String, OAuth2ClientDO> clientCache;

    /**
     * approve缓存
     * 格式：（用户id+客户端id）+批准信息
     */
    private Cache<String, List<OAuth2Approve>> approveCache;

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

        QuickConfig clientQc = QuickConfig.newBuilder(OAUTH_CLIENT)
                .expire(properties.getClientExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        QuickConfig approveQc = QuickConfig.newBuilder(OAUTH_APPROVE)
                //TODO 提出配置文件
                .expire(properties.getClientExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        tokenCache = cacheManager.getOrCreateCache(tokenQc);
        refreshTokenCache = cacheManager.getOrCreateCache(refreshTokenQc);
        clientCache = cacheManager.getOrCreateCache(clientQc);
        approveCache = cacheManager.getOrCreateCache(approveQc);
    }

    @Bean("tokenCache")
    public Cache<String, LoginUser> getTokenCache() {
        return tokenCache;
    }

    @Bean("refreshTokenCache")
    public Cache<String, LoginUser> getRefreshToken() {
        return refreshTokenCache;
    }

    @Bean
    public Cache<String, OAuth2ClientDO> getClientCache() {
        return clientCache;
    }

    @Bean
    public Cache<String, List<OAuth2Approve>> getApproveCache() {
        return approveCache;
    }
}
