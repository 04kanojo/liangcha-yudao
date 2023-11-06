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

    @Resource
    private Cache<String, String> allTokenCache;


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
        // 获取客户端id，accessToken和refreshToken过期时间(秒)
        String clientId = client.getClientId();
        Integer accessTokenExSeconds = client.getAccessTokenValiditySeconds();
        Integer refreshTokenExSeconds = client.getRefreshTokenValiditySeconds();

        //获取默认过期时间
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(properties.getTokenExpireTimes().toMinutes());

        // 非默认客户端 自定义过期时间
        if (!CLIENT_ID_DEFAULT.equals(clientId)) {
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
        allTokenCache.put(user.getAccessToken(), clientId);
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
    public void removeToken(String accessToken, String clientId) {
        LoginUser user = getUserByAccessToken(accessToken, clientId);
        // 未获取到用户直接返回
        if (user == null) {
            return;
        }
        // 从redis里面删除
        tokenCache.remove(getKey(accessToken, clientId));
        removeRefreshToken(user.getRefreshToken(), clientId);
    }

    @Override
    public void removeRefreshToken(String refreshToken, String clientId) {
        LoginUser user = getUserByRefreshAccessToken(refreshToken, clientId);
        // 未获取到用户直接返回
        if (user == null) {
            return;
        }
        // TODO 如果remove键为空或者没有键是否报错 记得测试
        // 从redis里面删除
        refreshTokenCache.remove(getKey(user.getRefreshToken(), clientId));
    }

    @Override
    public LoginUser refreshToken(String refreshToken, String clientId) {
        // 1.refreshToken过期
        LoginUser user = getUserByRefreshAccessToken(refreshToken, clientId);
        if (user == null) {
            throw exception(FLUSH_TOKEN_NOT_EXIST, FLUSH_TOKEN_EXPIRED);
        }
        // 2.refreshToken未过期，正常刷新，先删除用户正在使用的token（可能有人犯贱accessToken未过期就调用接口），所以先从accessToken开始删
        removeToken(user.getAccessToken(), clientId);

        //构建登录用户
        OAuth2ClientDO client = oAuth2ClientService.validOAuthClientFromCache(clientId);
        return createAccessToken(user, client);
    }

    private String getKey(String token, String clientId) {
        return StringUtil.join("-", clientId, token);
    }
}
