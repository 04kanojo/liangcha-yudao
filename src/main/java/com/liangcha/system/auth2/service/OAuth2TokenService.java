package com.liangcha.system.auth2.service;


import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * OAuth2.0 Token API 接口
 * 注:因为关于token的所有操作都可能被其他客户端调用,所以方法体里面有客户端参数
 * <p>
 * token状态有4种：
 * 1.accessToken未过期 refreshToken未过期：正常访问接口
 * 2.accessToken过期 refreshToken未过期：使用refreshToken刷新accessToken（同时refreshToken也会更改）
 * 3.accessToken未过期 refreshToken过期：正常访问接口，直到refreshToken过期，成为下种情况
 * 4.accessToken过期 refreshToken过期：使用refreshToken访问接口的时候报错，用户再次登录
 *
 * @author 凉茶
 */
public interface OAuth2TokenService {

    /**
     * 创建访问令牌
     * 用于默认客户端,不需要授权范围和request
     *
     * @return 登录用户的信息
     */
    default LoginUser createAccessToken(Long userId, String clientId) {
        return createAccessToken(userId, clientId, null, null);
    }

    /**
     * 创建访问令牌 第二阶段
     *
     * @param user 登录用户
     * @return 登录用户的信息
     */
    LoginUser createAccessToken(LoginUser user, OAuth2ClientDO client, HttpServletRequest request);

    /**
     * 创建访问令牌 第一阶段
     *
     * @param userId   用户编号
     * @param clientId 客户端编号
     * @param scopes   授权范围
     * @return 访问令牌的信息
     */
    LoginUser createAccessToken(Long userId, String clientId, List<String> scopes, HttpServletRequest request);

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
    void removeToken(String accessToken, String clientId);

    /**
     * 移除刷新令牌
     *
     * @param refreshToken 刷新令牌
     * @param clientId     客户端id
     */
    void removeRefreshToken(String refreshToken, String clientId);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @param clientId     客户端id
     * @return 登录用户的信息
     */
    LoginUser refreshToken(String refreshToken, String clientId);

}
