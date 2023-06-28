package com.liangcha.pojo.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.pojo.auth.db.OAuth2CodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2CodeMapper extends BaseMapper<OAuth2CodeDO> {
}
