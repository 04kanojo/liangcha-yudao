package com.liangcha.framework.redis;

import com.alicp.jetcache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 公共的缓存
 * 暂无
 *
 * @author 凉茶
 */
@Component
public class RedisCache {
    @Resource
    protected CacheManager cacheManager;
}
