package com.liangcha.dao.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.domain.auth.AdminUserDO;
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
}
