package com.liangcha.framework.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.framework.common.enums.UserTypeEnum;
import com.liangcha.framework.permission.service.PermissionService;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static com.liangcha.framework.common.enums.ErrorCodeEnum.FLUSH_TOKEN_EXPIRED;
import static com.liangcha.framework.common.utils.ServiceExceptionUtil.exception;

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
    private AdminUserService adminUserService;

    @Resource
    private PermissionService permissionService;

    private static String generateToken() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public LoginUser createAccessToken(Long userId) {
        // 创建访问令牌
        LoginUser user = new LoginUser()
                .setId(userId)
                .setDeptId(adminUserService.getById(userId).getDeptId())
                .setRoles(permissionService.getEnableUserRoleListByUserId(userId))
                .setUserType(UserTypeEnum.ADMIN.getCode());

        //降低耦合性
        return createAccessToken(user);
    }

    @Override
    public LoginUser createAccessToken(LoginUser user) {
        user
                .setAccessToken(generateToken())
                .setRefreshToken(generateToken())
                .setExpiresTime(LocalDateTime.now().plusMinutes(20));

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
