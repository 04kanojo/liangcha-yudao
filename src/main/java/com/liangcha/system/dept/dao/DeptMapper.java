package com.liangcha.system.dept.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.dept.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {
    default List<DeptDO> selectListByParentId(Set<Long> parentId) {
        return selectList(new LambdaQueryWrapper<DeptDO>().eq(DeptDO::getParentId, parentId));
    }
}
