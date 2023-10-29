package com.liangcha.system.auth2.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.auth2.pojo.domain.OAuth2CodeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2CodeMapper extends BaseMapper<OAuth2CodeDO> {

    default OAuth2CodeDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<OAuth2CodeDO>().eq(OAuth2CodeDO::getCode, code));
    }

}
