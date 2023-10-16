package com.liangcha.framework.security.service.impl;

import com.liangcha.framework.security.service.SecurityFrameworkService;
import com.liangcha.system.permission.service.PermissionService;
import lombok.AllArgsConstructor;

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

}
