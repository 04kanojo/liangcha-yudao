package com.liangcha.system.module.permission.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.liangcha.system.module.permission.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author 凉茶
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    default List<UserRoleDO> selectListByUserId(Long userId) {
        return selectList(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, userId));
    }

    default void deleteListByUserIdAndRoleIdIds(Long userId, Collection<Long> roleIds) {
        delete(new LambdaQueryWrapper<UserRoleDO>()
                .eq(UserRoleDO::getUserId, userId)
                .in(UserRoleDO::getRoleId, roleIds));
    }

    default void deleteListByUserId(Long userId) {
        delete(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, userId));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getRoleId, roleId));
    }

    default List<UserRoleDO> selectListByRoleIds(Collection<Long> roleIds) {
        return selectList(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getRoleId, roleIds));
    }

    default void insertBatch(List<UserRoleDO> entityList) {
        Db.saveBatch(entityList);
    }
}
