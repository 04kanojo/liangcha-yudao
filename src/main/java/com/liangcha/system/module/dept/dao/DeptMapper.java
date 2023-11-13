package com.liangcha.system.module.dept.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.module.dept.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 凉茶
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {
    default List<DeptDO> selectListByParentId(Set<Long> parentId) {
        // 根据每一个集合元素查询数据库（根据id查询所有部门）
        return selectList(new LambdaQueryWrapper<DeptDO>().in(DeptDO::getParentId, parentId));
    }
}
