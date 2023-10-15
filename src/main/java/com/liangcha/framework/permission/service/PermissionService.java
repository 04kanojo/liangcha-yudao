package com.liangcha.framework.permission.service;

import com.liangcha.system.domain.permission.RoleDO;

import java.util.List;
import java.util.Set;


/**
 * 权限 Service 接口
 * <p>
 * 提供用户-角色、角色-菜单、角色-部门的关联权限处理
 *
 * @author 凉茶
 */
public interface PermissionService {

    /**
     * 判断是否有权限，任一一个即可
     *
     * @param userId      用户编号
     * @param permissions 权限
     * @return 是否
     */
    boolean hasAnyPermissions(Long userId, String... permissions);

    /**
     * 判断是否有角色，任一一个即可
     *
     * @param roles 角色数组
     * @return 是否
     */
    boolean hasAnyRoles(Long userId, String... roles);

    /**
     * 获得用户的启用角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<RoleDO> getEnableUserRoleListByUserId(Long userId);

    /**
     * 获得拥有指定菜单的角色编号数组
     *
     * @param menuId 菜单编号
     * @return 角色编号数组
     */
    Set<Long> getMenuRoleIdListByMenuId(Long menuId);


    /**
     * 获得用户拥有的角色编号集合
     *
     * @param userId 用户编号
     * @return 角色编号集合
     */
    Set<Long> getUserRoleIdListByUserId(Long userId);

}
