package com.liangcha.system.user.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.liangcha.system.auth.domain.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 凉茶
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {
    default AdminUserDO selectUserByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getUsername, username));
    }

    default AdminUserDO selectByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getMobile, mobile));
    }

    /**
     * 重写方法是因为需要加入数据权限功能，@Param注解复制的BaseMapper,不加这个属性报错
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return AdminUserDO
     */
    @Override
//    @DataPermission(key = {"deptName", "userName"}, value = {"dept_id", "user_id"})
    List<AdminUserDO> selectList(@Param(Constants.WRAPPER) Wrapper<AdminUserDO> queryWrapper);
}
