package com.liangcha.system.dao.auth;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.framework.dataPermission.DataPermission;
import com.liangcha.framework.security.pojo.domain.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapper<OAuth2ClientDO> {

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(new LambdaQueryWrapper<OAuth2ClientDO>().eq(OAuth2ClientDO::getClientId, clientId));
    }

    @Override
    @DataPermission(key = {"deptName", "userName"}, value = {"dept_id", "user_id"})
    List<OAuth2ClientDO> selectList(Wrapper<OAuth2ClientDO> queryWrapper);
}
