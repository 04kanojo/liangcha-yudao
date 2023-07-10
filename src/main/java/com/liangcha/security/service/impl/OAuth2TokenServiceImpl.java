package com.liangcha.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liangcha.common.exception.ServiceException;
import com.liangcha.common.redis.CacheService;
import com.liangcha.security.convert.service.OAuth2TokenConvert;
import com.liangcha.security.pojo.dao.OAuth2AccessTokenMapper;
import com.liangcha.security.pojo.dao.OAuth2RefreshTokenMapper;
import com.liangcha.security.pojo.db.OAuth2AccessTokenDO;
import com.liangcha.security.pojo.db.OAuth2ClientDO;
import com.liangcha.security.pojo.db.OAuth2RefreshTokenDO;
import com.liangcha.security.pojo.dto.OAuth2AccessTokenCheckRespDTO;
import com.liangcha.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;
import com.liangcha.security.pojo.dto.OAuth2AccessTokenRespDTO;
import com.liangcha.security.service.OAuth2ClientService;
import com.liangcha.security.service.OAuth2TokenService;
import com.liangcha.tenant.TenantContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OAuth2.0 Token API 实现类
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
    public OAuth2AccessTokenRespDTO createAccessToken(OAuth2AccessTokenCreateReqDTO reqDTO) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(reqDTO.getClientId(), null, null, null, null);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(reqDTO.getUserId(), reqDTO.getUserType(), clientDO, reqDTO.getScopes());
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = createOAuth2AccessToken(refreshTokenDO, clientDO);
        return OAuth2TokenConvert.INSTANCE.convert2(accessTokenDO);
    }

    @Override
    public OAuth2AccessTokenCheckRespDTO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = getAccessToken(accessToken);
        if (accessTokenDO == null) {
            throw new ServiceException("访问令牌不存在");
        }
        if (LocalDateTime.now().isAfter(accessTokenDO.getExpiresTime())) {
            throw new ServiceException("访问令牌已过期");
        }
        return OAuth2TokenConvert.INSTANCE.convert(accessTokenDO);
    }

    @Override
    public OAuth2AccessTokenRespDTO removeAccessToken(String accessToken) {
        // 删除访问令牌
        LambdaQueryWrapper<OAuth2AccessTokenDO> tokenLqw = new LambdaQueryWrapper<>();
        tokenLqw.eq(OAuth2AccessTokenDO::getAccessToken, accessToken);
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectOne(tokenLqw);
        if (accessTokenDO == null) {
            return null;
        }
        oauth2AccessTokenMapper.deleteById(accessTokenDO.getId());
        CacheService.tokenCache.remove("tokenCache_" + accessTokenDO.getUserId());

        // 删除刷新令牌
        LambdaQueryWrapper<OAuth2RefreshTokenDO> refreshTokenLqw = new LambdaQueryWrapper<>();
        refreshTokenLqw.eq(OAuth2RefreshTokenDO::getRefreshToken, accessTokenDO.getRefreshToken());
        oauth2RefreshTokenMapper.delete(refreshTokenLqw);

        return OAuth2TokenConvert.INSTANCE.convert2(accessTokenDO);
    }

    @Override
    public OAuth2AccessTokenRespDTO refreshAccessToken(String refreshToken, String clientId) {

        // 查询访问令牌
        LambdaQueryWrapper<OAuth2RefreshTokenDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken);
        OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectOne(lqw);

        if (refreshTokenDO == null) {
            throw new ServiceException("无效的刷新令牌");
        }

        // 校验 Client 匹配
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId, null, null, null, null);
        if (ObjectUtil.notEqual(clientId, refreshTokenDO.getClientId())) {
            throw new ServiceException("刷新令牌的客户端编号不正确");
        }

        // 移除相关的访问令牌
        LambdaQueryWrapper<OAuth2AccessTokenDO> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(OAuth2AccessTokenDO::getRefreshToken, refreshToken);
        List<OAuth2AccessTokenDO> accessTokenDOs = oauth2AccessTokenMapper.selectList(lqw1);

        if (CollUtil.isNotEmpty(accessTokenDOs)) {
            List<Long> ids = accessTokenDOs.stream().map(OAuth2AccessTokenDO::getId).collect(Collectors.toList());
            oauth2AccessTokenMapper.deleteBatchIds(ids);
//            oauth2AccessTokenRedisDAO.deleteList(convertSet(accessTokenDOs, OAuth2AccessTokenDO::getAccessToken));
        }

        // 已过期的情况下，删除刷新令牌
        if (LocalDateTime.now().isAfter(refreshTokenDO.getExpiresTime())) {
            oauth2RefreshTokenMapper.deleteById(refreshTokenDO.getId());
            throw new ServiceException("刷新令牌已过期");
        }

        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = createOAuth2AccessToken(refreshTokenDO, clientDO);

        return OAuth2TokenConvert.INSTANCE.convert2(accessTokenDO);
    }

    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
        // 优先从 Redis 中获取
        OAuth2AccessTokenDO accessTokenDO = CacheService.tokenCache.get(accessToken);
        if (accessTokenDO != null) {
            return accessTokenDO;
        }

        // 获取不到，从 MySQL 中获取
        LambdaQueryWrapper<OAuth2AccessTokenDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(OAuth2AccessTokenDO::getAccessToken, accessToken);
        accessTokenDO = oauth2AccessTokenMapper.selectOne(lqw);
        // 如果在 MySQL 存在，则往 Redis 中写入
        if (accessTokenDO != null && !LocalDateTime.now().isAfter(accessTokenDO.getExpiresTime())) {
            CacheService.tokenCache.put("tokenCache_" + accessTokenDO.getUserId(), accessTokenDO);
        }
        return accessTokenDO;
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
        //手动设置,因为这个属性是继承的
        accessTokenDO.setTenantId(TenantContextHolder.getTenantId());
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // 记录到 Redis 中
        CacheService.tokenCache.put("tokenCache_" + accessTokenDO.getUserId(), accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(generateToken()).setUserId(userId).setUserType(userType).setClientId(clientDO.getClientId()).setScopes(scopes).setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

}