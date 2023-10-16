package com.liangcha.system.user.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.dataPermission.DataPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 凉茶
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {
    default AdminUserDO selectUserByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getUsername, username));
    }

    default AdminUserDO selectByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getMobile, mobile));
    }

    @Override
    @DataPermission(key = {"deptName"}, value = {"dept_id"})
    List<AdminUserDO> selectList(Wrapper<AdminUserDO> queryWrapper);
}
