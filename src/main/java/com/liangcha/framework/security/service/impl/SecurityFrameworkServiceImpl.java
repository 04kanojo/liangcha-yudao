package com.liangcha.framework.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.liangcha.framework.security.service.SecurityFrameworkService;
import com.liangcha.system.module.auth2.pojo.LoginUser;
import com.liangcha.system.module.permission.service.PermissionService;
import lombok.AllArgsConstructor;

import java.util.Arrays;

import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUser;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUserId;

/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 * @author 凉茶
 */
@AllArgsConstructor
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {

    private final PermissionService permissionService;

    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermissions(permission);
    }

    @Override
    public boolean hasAnyPermissions(String... permissions) {
        return permissionService.hasAnyPermissions(getLoginUserId(), permissions);
    }

    @Override
    public boolean hasRole(String role) {
        return hasAnyRoles(role);
    }

    @Override
    public boolean hasAnyRoles(String... roles) {
        return permissionService.hasAnyRoles(getLoginUserId(), roles);
    }

    @Override
    public boolean hasScope(String scope) {
        return hasAnyScopes(scope);
    }

    @Override
    public boolean hasAnyScopes(String... scope) {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return false;
        }
        return CollUtil.containsAny(loginUser.getScopes(), Arrays.asList(scope));
    }

}
