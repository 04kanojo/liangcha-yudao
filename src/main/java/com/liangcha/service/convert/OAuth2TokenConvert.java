package com.liangcha.service.convert;

import cn.hutool.db.PageResult;
import com.liangcha.pojo.auth.db.OAuth2AccessTokenDO;
import com.liangcha.pojo.auth.dto.OAuth2AccessTokenCheckRespDTO;
import com.liangcha.pojo.auth.dto.OAuth2AccessTokenRespDTO;
import com.liangcha.pojo.auth.vo.OAuth2AccessTokenRespVO;
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
