package com.liangcha.system.auth2.service;

import cn.hutool.core.util.IdUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.common.utils.StringUtil;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import com.liangcha.system.permission.service.PermissionService;
import com.liangcha.system.user.enums.UserTypeEnum;
import com.liangcha.system.user.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.liangcha.common.enums.ErrorCodeEnum.FLUSH_TOKEN_EXPIRED;
import static com.liangcha.common.enums.ErrorCodeEnum.FLUSH_TOKEN_NOT_EXIST;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.system.auth2.enums.OAuth2ClientConstants.CLIENT_ID_DEFAULT;

/**
 * OAuth2.0 Token Service 实现类
 *
 * @author 凉茶
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {
    @Resource
    private Cache<String, LoginUser> tokenCache;

    @Resource
    private Cache<String, LoginUser> refreshTokenCache;

    @Resource
    private PermissionService permissionService;

    @Resource
    private AdminUserService userService;

    @Resource
    private OAuth2ClientService oAuth2ClientService;

    @Resource
    private SecurityProperties properties;


    /**
     * 生成随机字符串
     * TODO 可优化成：加密(用户id+时间戳+签名+..)
     */
    private static String generateToken() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public LoginUser createAccessToken(Long userId, String clientId, List<String> scopes) {
        //判断客户端（oauth2那部分其他客户端会调用此接口）
        OAuth2ClientDO client = oAuth2ClientService.validOAuthClientFromCache(clientId);
        // 创建访问令牌
        LoginUser user = new LoginUser()
                .setUserId(userId)
                .setScopes(scopes)
                .setClientId(clientId)
                .setUserType(UserTypeEnum.ADMIN.getCode())
                .setRoles(permissionService.getEnableUserRoleListByUserId(userId))
                .setDeptId(userService.getById(userId).getDeptId());
        //降低耦合性
        return createAccessToken(user, client);
    }

    @Override
    public LoginUser createAccessToken(LoginUser user, OAuth2ClientDO client) {
        // 获取客户端accessToken和refreshToken过期时间(秒)
        Integer accessTokenExSeconds = client.getAccessTokenValiditySeconds();
        Integer refreshTokenExSeconds = client.getRefreshTokenValiditySeconds();

        //获取默认过期时间
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(properties.getTokenExpireTimes().toMinutes());

        // 非默认客户端 自定义过期时间
        if (!CLIENT_ID_DEFAULT.equals(client.getClientId())) {
            localDateTime = LocalDateTime.now().plusSeconds(accessTokenExSeconds);
            tokenCache.config().setExpireAfterWriteInMillis(accessTokenExSeconds * 1000L);
            refreshTokenCache.config().setExpireAfterWriteInMillis(refreshTokenExSeconds * 1000L);
        }

        // 设置用户参数
        user
                .setAccessToken(generateToken())
                .setRefreshToken(generateToken())
                .setExpiresTime(localDateTime);

        // 存入缓存
        tokenCache.put(getKey(user.getAccessToken(), client.getClientId()), user);
        refreshTokenCache.put(getKey(user.getRefreshToken(), client.getClientId()), user);
        return user;
    }

    @Override
    public LoginUser getUserByAccessToken(String accessToken, String clientId) {
        return tokenCache.get(getKey(accessToken, clientId));
    }

    @Override
    public LoginUser getUserByRefreshAccessToken(String refreshAccessToken, String clientId) {
        return refreshTokenCache.get(getKey(refreshAccessToken, clientId));
    }

    @Override
    public void removeToken(String token, String clientId) {
        // 1.传递过来的是accessToken
        LoginUser user = getUserByAccessToken(token, clientId);
        if (user != null) {
            tokenCache.remove(getKey(token, clientId));
            refreshTokenCache.remove(getKey(user.getRefreshToken(), clientId));
            return;
        }

        // 2.传递过来的是refreshToken
        user = getUserByRefreshAccessToken(token, clientId);
        if (user != null) {
            refreshTokenCache.remove(getKey(user.getRefreshToken(), clientId));
        }
    }

    @Override
    public LoginUser refreshToken(String refreshToken, String clientId) {
        // 1.refreshToken过期
        LoginUser user = refreshTokenCache.get(getKey(refreshToken, clientId));
        if (user == null) {
            throw exception(FLUSH_TOKEN_NOT_EXIST, FLUSH_TOKEN_EXPIRED);
        }

        // 2.refreshToken未过期，正常刷新，先删除用户之前使用的refreshToken
        removeToken(user.getRefreshToken(), clientId);
        OAuth2ClientDO client = oAuth2ClientService.validOAuthClientFromCache(clientId);
        return createAccessToken(user, client);
    }

    private String getKey(String token, String clientId) {
        return StringUtil.join("-", clientId, token);
    }
}
