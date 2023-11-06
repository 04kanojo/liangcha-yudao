package com.liangcha.system.dept.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.common.enums.CommonStatusEnum;
import com.liangcha.system.dept.domain.PostDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<PostDO> {
    default List<PostDO> selectList(Collection<Long> ids) {
        return selectList(new LambdaQueryWrapper<PostDO>().eq(PostDO::getStatus, CommonStatusEnum.ENABLE.getStatus()).in(PostDO::getId, ids));
    }
}
