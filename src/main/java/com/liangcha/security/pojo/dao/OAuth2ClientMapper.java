package com.liangcha.security.pojo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.security.pojo.db.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapper<OAuth2ClientDO> {
}
