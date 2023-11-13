package com.liangcha.system.module.auth.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.module.auth2.pojo.domain.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapper<OAuth2ClientDO> {

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(new LambdaQueryWrapper<OAuth2ClientDO>().eq(OAuth2ClientDO::getClientId, clientId));
    }
}
