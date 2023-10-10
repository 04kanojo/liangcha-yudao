package com.liangcha.system.dao.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.domain.auth.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

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
}
