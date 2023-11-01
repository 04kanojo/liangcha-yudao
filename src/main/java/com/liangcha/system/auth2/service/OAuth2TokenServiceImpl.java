package com.liangcha.system.auth2.service;

import cn.hutool.core.util.IdUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.permission.service.PermissionService;
import com.liangcha.system.user.enums.UserTypeEnum;
import com.liangcha.system.user.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.liangcha.common.enums.ErrorCodeEnum.FLUSH_TOKEN_EXPIRED;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;

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
        // 创建访问令牌
        LoginUser user = new LoginUser()
                .setUserId(userId)
                .setScopes(scopes)
                .setClientId(clientId)
                .setUserType(UserTypeEnum.ADMIN.getCode())
                .setRoles(permissionService.getEnableUserRoleListByUserId(userId))
                .setDeptId(userService.getById(userId).getDeptId());
        //降低耦合性
        return createAccessToken(user);
    }

    @Override
    public LoginUser createAccessToken(LoginUser user) {
        user
                .setAccessToken(generateToken())
                .setRefreshToken(generateToken())
                .setExpiresTime(LocalDateTime.now().plusMinutes(properties.getTokenExpireTimes().toMinutes()));
        tokenCache.put(user.getAccessToken(), user);
        refreshTokenCache.put(user.getRefreshToken(), user);
        return user;
    }

    @Override
    public LoginUser getUserByAccessToken(String accessToken) {
        return tokenCache.get(accessToken);
    }

    @Override
    public LoginUser getUserByRefreshAccessToken(String refreshAccessToken) {
        return refreshTokenCache.get(refreshAccessToken);
    }

    @Override
    public void removeAccessToken(String accessToken) {
        LoginUser user = tokenCache.get(accessToken);
        // 令牌不存在直接结束
        if (user == null) {
            return;
        }
        // 删除令牌
        tokenCache.remove(accessToken);
        refreshTokenCache.remove(user.getRefreshToken());
    }

    @Override
    public LoginUser refreshToken(String refreshToken) {
        LoginUser user = refreshTokenCache.get(refreshToken);
        if (user == null) {
            throw exception(FLUSH_TOKEN_EXPIRED);
        }
        return createAccessToken(user);
    }

}
