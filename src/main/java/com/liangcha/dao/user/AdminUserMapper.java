package com.liangcha.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.domain.auth.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 凉茶
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {

}
