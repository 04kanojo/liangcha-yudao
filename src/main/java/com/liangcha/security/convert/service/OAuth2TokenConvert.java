package com.liangcha.security.convert.service;

import cn.hutool.db.PageResult;
import com.liangcha.security.pojo.db.OAuth2AccessTokenDO;
import com.liangcha.security.pojo.dto.OAuth2AccessTokenCheckRespDTO;
import com.liangcha.security.pojo.dto.OAuth2AccessTokenRespDTO;
import com.liangcha.security.pojo.vo.OAuth2AccessTokenRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2TokenConvert {

    OAuth2TokenConvert INSTANCE = Mappers.getMapper(OAuth2TokenConvert.class);

    OAuth2AccessTokenCheckRespDTO convert(OAuth2AccessTokenDO bean);

    PageResult<OAuth2AccessTokenRespVO> convert(PageResult<OAuth2AccessTokenDO> page);

    OAuth2AccessTokenRespDTO convert2(OAuth2AccessTokenDO bean);

}
