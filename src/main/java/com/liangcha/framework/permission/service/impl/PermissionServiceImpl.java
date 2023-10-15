package com.liangcha.framework.permission.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alicp.jetcache.anno.Cached;
import com.google.common.collect.Sets;
import com.liangcha.framework.common.enums.CommonStatusEnum;
import com.liangcha.framework.common.enums.RedisKeyConstants;
import com.liangcha.framework.permission.service.MenuService;
import com.liangcha.framework.permission.service.PermissionService;
import com.liangcha.framework.permission.service.RoleService;
import com.liangcha.system.dao.permission.RoleMenuMapper;
import com.liangcha.system.dao.permission.UserRoleMapper;
import com.liangcha.system.domain.permission.RoleDO;
import com.liangcha.system.domain.permission.RoleMenuDO;
import com.liangcha.system.domain.permission.UserRoleDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.liangcha.framework.common.enums.RedisKeyConstants.USER_ROLE_ID_LIST;
import static com.liangcha.framework.common.utils.CollectionUtils.convertSet;

/**
 * 权限 Service 实现类
 *
 * @author 凉茶
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;


    @Override
    public boolean hasAnyPermissions(Long userId, String... permissions) {
        // 获得当前登录的角色集合
        List<RoleDO> roles = getEnableUserRoleListByUserId(userId);

        // 如果为空，说明没有权限
        if (CollUtil.isEmpty(roles)) {
            return false;
        }

        // 情况一：遍历判断每个权限，如果有一满足，说明有权限
        for (String permission : permissions) {
            if (hasAnyPermission(roles, permission)) {
                return true;
            }
        }

        // 情况二：如果是超管，也说明有权限
        return roleService.isSuperAdmin(convertSet(roles, RoleDO::getId));
    }

    /**
     * 判断指定角色，是否拥有该 permission 权限
     *
     * @param roles      指定角色数组
     * @param permission 权限标识
     * @return 是否拥有
     */
    private boolean hasAnyPermission(List<RoleDO> roles, String permission) {
        //登录用户的角色id集合
        Set<Long> roleIds = convertSet(roles, RoleDO::getId);

        //接口应有权限的菜单id集合
        List<Long> menuIds = menuService.getMenuIdListByPermissionFromCache(permission);

        // 权限找不到对应的 Menu 的话，也认为没有权限
        if (CollUtil.isEmpty(menuIds)) {
            return false;
        }

        // 判断是否有权限
        for (Long menuId : menuIds) {
            // 获得拥有该菜单的角色编号集合
            Set<Long> menuRoleIds = getSelf().getMenuRoleIdListByMenuId(menuId);
            // 如果有交集，说明有权限
            if (CollUtil.containsAny(menuRoleIds, roleIds)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean hasAnyRoles(Long userId, String... roles) {
        // 获得当前登录的角色。如果为空，说明没有权限
        List<RoleDO> roleList = getEnableUserRoleListByUserId(userId);
        if (CollUtil.isEmpty(roleList)) {
            return false;
        }

        // 判断是否有角色
        Set<String> userRoles = convertSet(roleList, RoleDO::getCode);
        return CollUtil.containsAny(userRoles, Sets.newHashSet(roles));
    }

    /**
     * 获得用户拥有的角色，并且这些角色是开启状态的
     *
     * @param userId 用户编号
     * @return 用户拥有的角色
     */
    @Override
    public List<RoleDO> getEnableUserRoleListByUserId(Long userId) {
        // 获得用户拥有的角色编号
        Set<Long> roleIds = getSelf().getUserRoleIdListByUserId(userId);
        // 获得角色数组，并移除被禁用的
        List<RoleDO> roles = roleService.getRoleListByIds(roleIds);
        roles.removeIf(role -> !CommonStatusEnum.ENABLE.getStatus().equals(role.getStatus()));
        return roles;
    }

    @Override
    @Cached(name = RedisKeyConstants.MENU_ROLE_ID_LIST, key = "#menuId", expire = 1, timeUnit = TimeUnit.HOURS)
    public Set<Long> getMenuRoleIdListByMenuId(Long menuId) {
        return convertSet(roleMenuMapper.selectListByMenuId(menuId), RoleMenuDO::getRoleId);
    }


    @Override
    @Cached(name = USER_ROLE_ID_LIST, key = "#userId", expire = 1, timeUnit = TimeUnit.HOURS)
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return convertSet(userRoleMapper.selectListByUserId(userId), UserRoleDO::getRoleId);
    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private PermissionServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }

}
