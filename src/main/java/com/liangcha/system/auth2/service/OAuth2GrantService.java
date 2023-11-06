package com.liangcha.system.auth2.service;

import com.liangcha.system.auth2.pojo.LoginUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * OAuth2 授予 Service 接口
 * <p>
 * 从功能上，和 Spring Security OAuth 的 TokenGranter 的功能，提供访问令牌、刷新令牌的操作
 * <p>
 * 将自身的 AdminUser 用户，授权给第三方应用，采用 OAuth2.0 的协议。
 * <p>
 * 问题：为什么自身也作为一个第三方应用，也走这套流程呢？
 * 回复：当然可以这么做，采用 password 模式。考虑到大多数开发者使用不到这个特性，OAuth2.0 毕竟有一定学习成本，所以暂时没有采取这种方式。
 *
 * @author 芋道源码
 */
public interface OAuth2GrantService {

    /**
     * 授权码模式，第二阶段，获得 accessToken 访问令牌
     *
     * @param clientId    客户端编号
     * @param code        授权码
     * @param redirectUri 重定向 URI
     * @param state       状态
     * @return 登录用户
     */
    LoginUser grantAuthorizationCodeForAccessToken(String clientId, String code, String redirectUri, String state, HttpServletRequest request);

    /**
     * 密码模式
     * <p>
     * 对应 Spring Security OAuth2 的 ResourceOwnerPasswordTokenGranter 功能
     *
     * @param username 账号
     * @param password 密码
     * @param clientId 客户端编号
     * @param scopes   授权范围
     * @return 访问令牌
     */
    LoginUser grantPassword(String username, String password, String clientId, List<String> scopes, HttpServletRequest request);


    /**
     * 授权码模式，第一阶段，获得 code 授权码
     * 对应 Spring Security OAuth2 的 AuthorizationEndpoint 的 generateCode 方法
     *
     * @param userId      用户编号
     * @param userType    用户类型
     * @param clientId    客户端编号
     * @param scopes      授权范围
     * @param redirectUri 重定向 URI
     * @param state       状态
     * @return 授权码
     */
    String grantAuthorizationCodeForCode(Long userId, Integer userType, String clientId, List<String> scopes, String redirectUri, String state);

    /**
     * 刷新模式
     * <p>
     * 对应 Spring Security OAuth2 的 ResourceOwnerPasswordTokenGranter 功能
     *
     * @param refreshToken 刷新令牌
     * @param clientId     客户端编号
     * @return 访问令牌
     */
    LoginUser grantRefreshToken(String refreshToken, String clientId);
}
