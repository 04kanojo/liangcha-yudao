package com.liangcha.system.permission.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.permission.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {


    default RoleDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getName, name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getCode, code));
    }

    default List<RoleDO> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getStatus, statuses));
    }

    default List<RoleDO> selectDesignateByUserId(Long roleId) {
        return selectList(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getId, roleId));
    }
}
