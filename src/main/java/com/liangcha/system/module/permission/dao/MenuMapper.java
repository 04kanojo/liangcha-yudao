package com.liangcha.system.module.permission.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.controller.promission.vo.MenuListReqVO;
import com.liangcha.system.module.permission.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 凉茶
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

    default MenuDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<MenuDO>().eq(MenuDO::getParentId, parentId).eq(MenuDO::getName, name));
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(new LambdaQueryWrapper<MenuDO>().eq(MenuDO::getParentId, parentId));
    }

    default List<MenuDO> selectList(MenuListReqVO reqVO) {
        return selectList(new LambdaQueryWrapper<MenuDO>()
                .like(MenuDO::getName, reqVO.getName())
                .eq(MenuDO::getStatus, reqVO.getStatus()));
    }

    default List<MenuDO> selectListByPermission(String permission) {
        return selectList(new LambdaQueryWrapper<MenuDO>().eq(MenuDO::getPermission, permission));
    }
}
