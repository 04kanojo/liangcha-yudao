package com.liangcha.common.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import com.liangcha.security.pojo.db.OAuth2AccessTokenDO;
import com.liangcha.security.pojo.db.OAuth2ClientDO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * 创建各种缓存类
 *
 * @author 凉茶
 */
@Data
@Component
public class CacheService {
    /**
     * 用户缓存
     * key：tokenCache_ + 用户id
     */
    public static Cache<String, OAuth2AccessTokenDO> tokenCache;

    /**
     * 客户端缓存
     * key：客户端编号
     */
    public static Cache<String, OAuth2ClientDO> clientCache;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        QuickConfig tokenQc = QuickConfig.newBuilder("tokenCache")
                .expire(Duration.ofSeconds(100))
                .cacheType(CacheType.LOCAL)
                .localLimit(50)
                .syncLocal(true)
                .build();
        tokenCache = cacheManager.getOrCreateCache(tokenQc);

        QuickConfig clientQc = QuickConfig.newBuilder("clientCache")
                .expire(Duration.ofSeconds(100))
                .cacheType(CacheType.LOCAL)
                .localLimit(50)
                .syncLocal(true)
                .build();
        clientCache = cacheManager.getOrCreateCache(clientQc);
    }
}
