package com.liangcha.framework.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.liangcha.dao.auth.OAuth2ClientMapper;
import com.liangcha.framework.common.enums.CommonStatusEnum;
import com.liangcha.framework.common.enums.ErrorCodeEnum;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.common.redis.RedisKeyConstants;
import com.liangcha.framework.security.mq.OAuth2ClientProducer;
import com.liangcha.framework.security.pojo.domain.OAuth2ClientDO;
import com.liangcha.framework.security.service.OAuth2ClientService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * OAuth2.0 Client Service 实现类
 *
 * @author 凉茶
 */
@Component
@Validated
public class OAuth2ClientServiceImpl implements OAuth2ClientService {

    @Resource
    private OAuth2ClientMapper oauth2ClientMapper;
    @Resource
    private OAuth2ClientProducer oauth2ClientProducer;

    /**
     * 给定字符串是否以任何一个字符串开始
     * 给定字符串和数组为空都返回 false
     *
     * @param str      给定字符串
     * @param prefixes 需要检测的开始字符串
     */
    public static boolean startWithAny(String str, Collection<String> prefixes) {
        if (StrUtil.isEmpty(str) || ArrayUtil.isEmpty(prefixes)) {
            return false;
        }

        for (CharSequence suffix : prefixes) {
            if (StrUtil.startWith(str, suffix, false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String authorizedGrantType, Collection<String> scopes, String redirectUri) {
        // 校验客户端是否存在
        OAuth2ClientDO client = getSelf().getOAuth2ClientFromCache(clientId);
        if (client == null) {
            throw new ServiceException(ErrorCodeEnum.OAUTH2_CLIENT_NOT_EXISTS);

        }
        if (ObjectUtil.notEqual(client.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw new ServiceException(ErrorCodeEnum.OAUTH2_CLIENT_DISABLE);
        }

        // 校验客户端密钥
        if (StrUtil.isNotEmpty(clientSecret) && ObjectUtil.notEqual(client.getSecret(), clientSecret)) {
            throw new ServiceException(ErrorCodeEnum.OAUTH2_CLIENT_CLIENT_SECRET_ERROR);

        }
        // 校验授权方式
        if (StrUtil.isNotEmpty(authorizedGrantType) && !CollUtil.contains(client.getAuthorizedGrantTypes(), authorizedGrantType)) {
            throw new ServiceException(ErrorCodeEnum.OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS);

        }
        // 校验授权范围
        if (CollUtil.isNotEmpty(scopes) && !CollUtil.containsAll(client.getScopes(), scopes)) {
            throw new ServiceException(ErrorCodeEnum.OAUTH2_CLIENT_SCOPE_OVER);
        }
        // 校验回调地址
        if (StrUtil.isNotEmpty(redirectUri) && !startWithAny(redirectUri, client.getRedirectUris())) {
            throw new ServiceException(ErrorCodeEnum.OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH);
        }
        return client;
    }

    @Cacheable(cacheNames = RedisKeyConstants.OAUTH_CLIENT, key = "#clientId",
            unless = "#result == null")
    public OAuth2ClientDO getOAuth2ClientFromCache(String clientId) {
        return oauth2ClientMapper.selectByClientId(clientId);
    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private OAuth2ClientServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }
}
