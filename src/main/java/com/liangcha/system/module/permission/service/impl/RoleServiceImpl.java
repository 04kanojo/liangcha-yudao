package com.liangcha.system.module.permission.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alicp.jetcache.anno.Cached;
import com.liangcha.common.utils.CollectionUtils;
import com.liangcha.system.module.permission.dao.RoleMapper;
import com.liangcha.system.module.permission.domain.RoleDO;
import com.liangcha.system.module.permission.enums.RoleCodeEnum;
import com.liangcha.system.module.permission.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.liangcha.common.utils.CollectionUtils.convertList;
import static com.liangcha.framework.redis.RedisKeyConstants.ROLE;

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
    @Cached(name = ROLE, key = "#id", expire = 10, timeUnit = TimeUnit.MINUTES)
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
    public String getDesignateDeptById(Long id) {
        RoleDO role = getSelf().getRoleById(id);
        return CollectionUtils.join(role.getDataScopeDeptIds(), String::valueOf, ",");
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
