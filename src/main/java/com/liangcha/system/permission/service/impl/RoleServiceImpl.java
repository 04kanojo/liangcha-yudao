package com.liangcha.system.permission.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.liangcha.framework.redis.RedisKeyConstants;
import com.liangcha.system.permission.dao.RoleMapper;
import com.liangcha.system.permission.domain.RoleDO;
import com.liangcha.system.permission.enums.RoleCodeEnum;
import com.liangcha.system.permission.service.RoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.liangcha.common.utils.CollectionUtils.convertList;

/**
 * 角色 Service 实现类
 *
 * @author 凉茶
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    @Cacheable(value = RedisKeyConstants.ROLE, key = "#id",
            unless = "#result == null")
    public RoleDO getRoleById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<RoleDO> getRoleListByIds(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // 这里采用 for 循环从缓存中获取，主要考虑 Spring CacheManager 无法批量操作的问题
        RoleServiceImpl self = getSelf();
        return convertList(ids, self::getRoleById);
    }

    @Override
    public boolean isSuperAdmin(Set<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return false;
        }
        RoleServiceImpl self = getSelf();
        for (Long id : ids) {
            RoleDO role = self.getRoleById(id);
            if (role != null && RoleCodeEnum.isSuperAdmin(role.getCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDeptAndChild(Long deptId) {
        return "success";
    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private RoleServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }

}
