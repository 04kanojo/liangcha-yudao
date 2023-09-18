package com.liangcha.dao.redis;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.liangcha.framework.common.util.JsonUtils;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.utils.CollectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.liangcha.framework.common.redis.RedisKeyConstants.OAUTH2_ACCESS_TOKEN;


/**
 * {@link OAuth2AccessTokenDO} 的 RedisDAO
 *
 * @author 芋道源码
 */
@Repository
public class OAuth2AccessTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static String formatKey(String accessToken) {
        return String.format(OAUTH2_ACCESS_TOKEN, accessToken);
    }

    public OAuth2AccessTokenDO get(String accessToken) {
        String redisKey = formatKey(accessToken);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), OAuth2AccessTokenDO.class);
    }

    public void set(OAuth2AccessTokenDO accessTokenDO) {
        String redisKey = formatKey(accessTokenDO.getAccessToken());
        // 清理多余字段，避免缓存
        //TODO basedo加上 @Accessors(chain=true) 注解开启set返回自己，但是不知道返回是父类是否影响子类
        accessTokenDO.setUpdater(null);
        accessTokenDO.setUpdateTime(null);
        accessTokenDO.setCreateTime(null);
        accessTokenDO.setCreator(null);
        accessTokenDO.setDeleted(null);
        long time = LocalDateTimeUtil.between(LocalDateTime.now(), accessTokenDO.getExpiresTime(), ChronoUnit.SECONDS);
        if (time > 0) {
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(accessTokenDO), time, TimeUnit.SECONDS);
        }
    }

    public void delete(String accessToken) {
        String redisKey = formatKey(accessToken);
        stringRedisTemplate.delete(redisKey);
    }

    public void deleteList(Collection<String> accessTokens) {
        List<String> redisKeys = CollectionUtils.convertList(accessTokens, OAuth2AccessTokenRedisDAO::formatKey);
        stringRedisTemplate.delete(redisKeys);
    }

}
