package com.liangcha.framework.permission.service;


import com.liangcha.system.domain.permission.RoleDO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 角色 Service 接口
 *
 * @author 凉茶
 */
public interface RoleService {

    /**
     * 获得角色
     *
     * @param id 角色编号
     * @return 角色
     */
    RoleDO getRoleById(Long id);


    /**
     * 获得角色数组
     *
     * @param ids 角色编号数组
     * @return 角色数组
     */
    List<RoleDO> getRoleListByIds(Collection<Long> ids);

    /**
     * 判断角色种是否有管理员角色
     *
     * @param ids 角色id集合
     */
    boolean isSuperAdmin(Set<Long> ids);
}
