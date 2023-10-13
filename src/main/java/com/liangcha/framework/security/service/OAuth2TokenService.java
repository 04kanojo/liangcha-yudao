package com.liangcha.framework.security.service;


import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.domain.OAuth2RefreshTokenDO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;

import javax.validation.Valid;

/**
 * OAuth2.0 Token API 接口
 *
 * @author 凉茶
 */
public interface OAuth2TokenService {

    /**
     * 创建令牌（访问和刷新）
     *
     * @param reqDTO 访问令牌的创建信息
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(@Valid OAuth2AccessTokenCreateReqDTO reqDTO);

    /**
     * 创建访问令牌
     *
     * @param refreshTokenDO 刷新令牌的信息
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO);

    /**
     * 创建刷新令牌
     *
     * @param userId   用户id
     * @param userType 用户类型
     */
    OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType);


    /**
     * 校验访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO checkAccessToken(String accessToken);

    /**
     * 移除访问令牌
     *
     * @param accessToken 访问令牌
     */
    void removeAccessToken(String accessToken);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO refreshToken(String refreshToken);

}
