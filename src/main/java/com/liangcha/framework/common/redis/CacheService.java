package com.liangcha.framework.common.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * 创建各种缓存类
 *
 * @author 凉茶
 */
@Data
@Service
public class CacheService {
    /**
     * 用户缓存的前缀
     */
    public final static String PRE_TOKEN_CACHE = "tokenCache_";


    /**
     * 用户缓存
     * key: tokenCache_ + token
     */
    public static Cache<String, OAuth2AccessTokenDO> tokenCache;

    /**
     * 客户端缓存
     * key：客户端编号
     */
//    public static Cache<String, OAuth2ClientDO> clientCache;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        QuickConfig tokenQc = QuickConfig.newBuilder("tokenCache")
                .expire(Duration.ofSeconds(100))
                .cacheType(CacheType.REMOTE)
                .localLimit(50)
                .syncLocal(true)
                .build();
        tokenCache = cacheManager.getOrCreateCache(tokenQc);
    }
}
