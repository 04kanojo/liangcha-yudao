package com.liangcha.system.auth2.service;


import com.liangcha.system.auth2.pojo.LoginUser;

import java.util.List;

/**
 * OAuth2.0 Token API 接口
 *
 * @author 凉茶
 */
public interface OAuth2TokenService {

    /**
     * 创建访问令牌
     *
     * @param user 登录用户
     * @return 登录用户的信息
     */
    LoginUser createAccessToken(LoginUser user);

    /**
     * 创建访问令牌
     *
     * @param userId   用户编号
     * @param clientId 客户端编号
     * @param scopes   授权范围
     * @return 访问令牌的信息
     */
    LoginUser createAccessToken(Long userId, String clientId, List<String> scopes);

    /**
     * 获取访问令牌
     *
     * @param accessToken 访问令牌
     * @return 登录用户的信息
     */
    LoginUser getUserByAccessToken(String accessToken);

    /**
     * 获取访问令牌
     *
     * @param refreshAccessToken 刷新令牌
     * @return 登录用户的信息
     */
    LoginUser getUserByRefreshAccessToken(String refreshAccessToken);

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
     * @return 登录用户的信息
     */
    LoginUser refreshToken(String refreshToken);

}
