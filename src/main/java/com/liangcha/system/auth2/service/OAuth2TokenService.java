package com.liangcha.system.auth2.service;


import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;

import java.util.List;

/**
 * OAuth2.0 Token API 接口
 * 注:因为关于token的所有操作都可能被其他客户端调用,所以方法体里面有客户端参数
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
    LoginUser createAccessToken(LoginUser user, OAuth2ClientDO client);

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
     * @param clientId    客户端id
     * @return 登录用户的信息
     */
    LoginUser getUserByAccessToken(String accessToken, String clientId);

    /**
     * 获取访问令牌
     *
     * @param refreshAccessToken 刷新令牌
     * @param clientId           客户端id
     * @return 登录用户的信息
     */
    LoginUser getUserByRefreshAccessToken(String refreshAccessToken, String clientId);

    /**
     * 移除访问令牌
     *
     * @param accessToken 访问令牌
     * @param clientId    客户端id
     */
    void removeAccessToken(String accessToken, String clientId);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @param clientId     客户端id
     * @return 登录用户的信息
     */
    LoginUser refreshToken(String refreshToken, String clientId);

}
