package com.liangcha.framework.convert.auth2;

import com.liangcha.server.system.controller.auth2.vo.OAuth2ClientRespVO;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * OAuth2 客户端 Convert
 *
 * @author 凉茶
 */
@Mapper
public interface OAuth2ClientConvert {

    OAuth2ClientConvert INSTANCE = Mappers.getMapper(OAuth2ClientConvert.class);


    OAuth2ClientRespVO convert(OAuth2ClientDO bean);


}
