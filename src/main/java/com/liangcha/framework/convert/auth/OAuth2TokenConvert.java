package com.liangcha.framework.convert.auth;

import cn.hutool.db.PageResult;
import com.liangcha.framework.security.pojo.OAuth2AccessToken;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCheckRespDTO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenRespDTO;
import com.liangcha.framework.security.pojo.vo.OAuth2AccessTokenRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2TokenConvert {

    OAuth2TokenConvert INSTANCE = Mappers.getMapper(OAuth2TokenConvert.class);

    OAuth2AccessTokenCheckRespDTO convert(OAuth2AccessToken bean);

    PageResult<OAuth2AccessTokenRespVO> convert(PageResult<OAuth2AccessToken> page);

    OAuth2AccessTokenRespDTO convert2(OAuth2AccessToken bean);

}
