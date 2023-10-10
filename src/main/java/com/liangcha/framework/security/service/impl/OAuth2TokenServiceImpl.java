package com.liangcha.framework.security.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.liangcha.framework.common.enums.ErrorCodeEnum;
import com.liangcha.framework.common.enums.RedisKeyConstants;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.domain.OAuth2ClientDO;
import com.liangcha.framework.security.pojo.domain.OAuth2RefreshTokenDO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;
import com.liangcha.framework.security.service.OAuth2ClientService;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.system.dao.auth.OAuth2AccessTokenMapper;
import com.liangcha.system.dao.auth.OAuth2RefreshTokenMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * OAuth2.0 Token Service 实现类
 *
 * @author 凉茶
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {
    @Resource
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Resource
    private OAuth2ClientService oauth2ClientService;


    @Resource
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    private static String generateToken() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public OAuth2AccessTokenDO createAccessToken(OAuth2AccessTokenCreateReqDTO reqDTO) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(reqDTO.getClientId(), null, null, null, null);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(reqDTO.getUserId(), reqDTO.getUserType(), clientDO, reqDTO.getScopes());
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO, clientDO);
    }


    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = getAccessTokenDO(accessToken);
        if (accessTokenDO == null) {
            throw new ServiceException(ErrorCodeEnum.ACCESS_TOKEN_NOT_EXIST);
        }
        if (LocalDateTime.now().isAfter(accessTokenDO.getExpiresTime())) {
            throw new ServiceException(ErrorCodeEnum.ACCESS_TOKEN_EXPIRED);
        }
        return accessTokenDO;
    }

    @Override
    public void removeAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
        // 令牌不存在直接结束
        if (accessTokenDO == null) {
            return;
        }
        // 删除令牌
        oauth2AccessTokenMapper.deleteById(accessTokenDO.getId());
        getSelf().deleteTokenInRedis(accessToken);
        // 删除刷新令牌
        oauth2RefreshTokenMapper.deleteByRefreshToken(accessTokenDO.getRefreshToken());
    }


    public OAuth2AccessTokenDO getAccessTokenDO(String accessToken) {
        return getSelf().getAccessTokenDOFromCache(accessToken);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, OAuth2ClientDO clientDO) {
        OAuth2AccessTokenDO accessTokenDO = OAuth2AccessTokenDO.builder()
                .accessToken(generateToken())
                .userId(refreshTokenDO.getUserId())
                .userType(refreshTokenDO.getUserType())
                .clientId(clientDO.getClientId())
                .scopes(refreshTokenDO.getScopes())
                .refreshToken(refreshTokenDO.getRefreshToken())
                .expiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()))
                .build();
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // 记录到 Redis 中
        getSelf().setTokenInRedis(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = OAuth2RefreshTokenDO.builder()
                .refreshToken(generateToken())
                .userId(userId)
                .userType(userType)
                .clientId(clientDO.getClientId())
                .scopes(scopes)
                .expiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()))
                .build();
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    @Cached(name = RedisKeyConstants.OAUTH2_ACCESS_TOKEN, key = "#accessToken", expire = 3, timeUnit = TimeUnit.MINUTES)
    public OAuth2AccessTokenDO getAccessTokenDOFromCache(String accessToken) {
        return oauth2AccessTokenMapper.selectByAccessToken(accessToken);
    }

    @Cached(name = RedisKeyConstants.OAUTH2_ACCESS_TOKEN, key = "#accessTokenDO.accessToken", expire = 3, timeUnit = TimeUnit.MINUTES)
    public OAuth2AccessTokenDO setTokenInRedis(OAuth2AccessTokenDO accessTokenDO) {
        return accessTokenDO;
    }

    @CacheInvalidate(name = RedisKeyConstants.OAUTH2_ACCESS_TOKEN, key = "#accessToken")
    public void deleteTokenInRedis(String accessToken) {

    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private OAuth2TokenServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }

}
