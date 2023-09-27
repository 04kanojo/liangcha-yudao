package com.liangcha.system.dao.permission;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.controller.role.vo.RoleExportReqVO;
import com.liangcha.system.controller.role.vo.RolePageReqVO;
import com.liangcha.system.domain.permission.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

    default PageResult<RoleDO> selectPage(RolePageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapper<RoleDO>()
//                .like(RoleDO::getName, reqVO.getName())
//                .like(RoleDO::getCode, reqVO.getCode())
//                .eq(RoleDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(BaseDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(RoleDO::getId));
        return null;
    }

    default List<RoleDO> selectList(RoleExportReqVO reqVO) {
//        return selectList(new LambdaQueryWrapper<RoleDO>()
//                .likeIfPresent(RoleDO::getName, reqVO.getName())
//                .likeIfPresent(RoleDO::getCode, reqVO.getCode())
//                .eqIfPresent(RoleDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(BaseDO::getCreateTime, reqVO.getCreateTime()));
        return null;
    }

    default RoleDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getName, name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getCode, code));
    }

    default List<RoleDO> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getStatus, statuses));
    }

}
