package com.liangcha.framework.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.domain.OAuth2RefreshTokenDO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.liangcha.framework.common.enums.ErrorCodeEnum.FLUSH_TOKEN_EXPIRED;
import static com.liangcha.framework.common.utils.ServiceExceptionUtil.exception;

/**
 * OAuth2.0 Token Service 实现类
 *
 * @author 凉茶
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {
    @Resource
    private Cache<String, OAuth2AccessTokenDO> tokenCache;

    @Resource
    private Cache<String, OAuth2RefreshTokenDO> refreshTokenCache;


    private static String generateToken() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public OAuth2AccessTokenDO createAccessToken(OAuth2AccessTokenCreateReqDTO reqDTO) {
//        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(reqDTO.getClientId(), null, null, null, null);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(reqDTO.getUserId(), reqDTO.getUserType());
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO);
    }


    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        return tokenCache.get(accessToken);
    }

    @Override
    public void removeAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = tokenCache.get(accessToken);
        // 令牌不存在直接结束
        if (accessTokenDO == null) {
            return;
        }
        // 删除令牌
        tokenCache.remove(accessToken);
        // 删除刷新令牌
        refreshTokenCache.remove(accessTokenDO.getRefreshToken());
    }

    @Override
    public OAuth2AccessTokenDO refreshToken(String refreshToken) {
        OAuth2RefreshTokenDO refreshTokenDO = refreshTokenCache.get(refreshToken);
        if (refreshTokenDO == null) {
            throw exception(FLUSH_TOKEN_EXPIRED);
        }
        return createOAuth2AccessToken(refreshTokenDO);
    }

    @Override
    public OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType) {
        OAuth2RefreshTokenDO refreshToken = OAuth2RefreshTokenDO.builder()
                .refreshToken(generateToken())
                .userId(userId)
                .userType(userType)
                .build();
        // 记录到 Redis 中
        refreshTokenCache.put(refreshToken.getRefreshToken(), refreshToken);
        return refreshToken;
    }

    @Override
    public OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO) {
        OAuth2AccessTokenDO accessTokenDO = OAuth2AccessTokenDO.builder()
                .accessToken(generateToken())
                .userId(refreshTokenDO.getUserId())
                .userType(refreshTokenDO.getUserType())
                .scopes(refreshTokenDO.getScopes())
                .refreshToken(refreshTokenDO.getRefreshToken())
                .build();

        // 记录到 Redis 中
        tokenCache.put(accessTokenDO.getAccessToken(), accessTokenDO);
        return accessTokenDO;
    }

}
