package com.liangcha.framework.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.module.auth2.pojo.LoginUser;
import com.liangcha.system.module.auth2.pojo.OAuth2Approve;
import com.liangcha.system.module.auth2.pojo.OAuth2Code;
import com.liangcha.system.module.auth2.pojo.domain.OAuth2ClientDO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

import static com.liangcha.framework.redis.RedisKeyConstants.*;

/**
 * Auth2相关缓存
 * <p>
 * accessToken和refreshToken为什么加上客户端的id
 * 答：第三方客户端存入accessToken和refreshToken和默认客户端同一个缓存，用来区分是哪个客户端的缓存(防止第三方客户端使用accessToken随便访问接口)
 *
 * @author 凉茶
 */
@Component
public class Auth2RedisCache extends RedisCache {

    @Resource
    private SecurityProperties properties;

    /**
     * 指定客户端accessToken缓存
     * 格式：(客户端id+token)+用户信息
     */
    private Cache<String, LoginUser> tokenCache;

    /**
     * 所有客户端accessToken缓存
     * 格式：token+客户端id
     * <p>
     * 用于第三方客户端访问时,在过滤器里找不到用户情况
     * TODO 还可以用session解决,但是我更倾向缓存
     */
    private Cache<String, String> allTokenCache;

    /**
     * refreshToken缓存
     * 格式：(客户端id+refreshToken)+用户信息
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

    /**
     * 授权code缓存
     * 格式：code+code信息
     */
    private Cache<String, OAuth2Code> codeCache;

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
                .expire(properties.getApproveExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        QuickConfig codeQc = QuickConfig.newBuilder(OAUTH_CODE)
                .expire(properties.getAuth2CodeExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        // 全部客户端过期时间默认使用默认客户端过期时间
        QuickConfig allTokenQc = QuickConfig.newBuilder(OAUTH2_ALL_ACCESS_TOKEN)
                .expire(properties.getTokenExpireTimes())
                // 二级缓存
                .cacheType(CacheType.BOTH)
                // 更新后使所有 JVM 进程中的本地缓存失效
                .syncLocal(false)
                .build();

        tokenCache = cacheManager.getOrCreateCache(tokenQc);
        refreshTokenCache = cacheManager.getOrCreateCache(refreshTokenQc);
        clientCache = cacheManager.getOrCreateCache(clientQc);
        approveCache = cacheManager.getOrCreateCache(approveQc);
        codeCache = cacheManager.getOrCreateCache(codeQc);
        allTokenCache = cacheManager.getOrCreateCache(allTokenQc);
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

    @Bean
    public Cache<String, OAuth2Code> getCodeCache() {
        return codeCache;
    }

    @Bean
    public Cache<String, String> getAllTokenCache() {
        return allTokenCache;
    }
}
