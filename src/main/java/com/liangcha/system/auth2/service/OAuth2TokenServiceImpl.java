package com.liangcha.system.auth2.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.common.utils.StringUtil;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import com.liangcha.system.permission.service.PermissionService;
import com.liangcha.system.user.enums.UserTypeEnum;
import com.liangcha.system.user.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.liangcha.common.enums.ErrorCodeEnum.*;
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
        // 获取客户端accessToken和refreshToken过期时间，并且修改缓存的过期时间
        Integer accessTokenExSeconds = client.getAccessTokenValiditySeconds();
        Integer refreshTokenExSeconds = client.getRefreshTokenValiditySeconds();
        // 设置用户参数
        user
                .setAccessToken(generateToken())
                .setRefreshToken(generateToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(accessTokenExSeconds));

        // 非默认客户端 额外设置自定义过期时间
        if (!CLIENT_ID_DEFAULT.equals(client.getClientId())) {
            tokenCache.config().setExpireAfterWriteInMillis(accessTokenExSeconds * 1000L);
            refreshTokenCache.config().setExpireAfterWriteInMillis(refreshTokenExSeconds * 1000L);
        }
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
    public void removeAccessToken(String accessToken, String clientId) {
        LoginUser user = getUserByAccessToken(accessToken, clientId);
        // 令牌不存在直接结束
        if (user == null) {
            return;
        }
        // 删除令牌
        tokenCache.remove(getKey(accessToken, clientId));
        refreshTokenCache.remove(getKey(user.getRefreshToken(), clientId));
    }

    @Override
    public LoginUser refreshToken(String refreshToken, String clientId) {
        // redis查询访问令牌
        LoginUser user = refreshTokenCache.get(refreshToken);
        if (user == null) {
            throw exception(FLUSH_TOKEN_NOT_EXIST, FLUSH_TOKEN_EXPIRED);
        }

        //TODO 有问题
        // 校验 Client 匹配
        OAuth2ClientDO client = oAuth2ClientService.validOAuthClientFromCache(clientId);
        if (ObjectUtil.notEqual(clientId, user.getClientId())) {
            throw exception(BAD_REQUEST.getCode(), "刷新令牌的客户端编号不正确");
        }

        return createAccessToken(user, client);
    }

    private String getKey(String token, String clientId) {
        return StringUtil.join("-", clientId, token);
    }
}
