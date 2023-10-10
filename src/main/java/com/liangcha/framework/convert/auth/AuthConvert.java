package com.liangcha.framework.convert.auth;

import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.controller.sms.dto.SmsCodeSendReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 凉茶
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginRespVO convert(OAuth2AccessTokenDO bean);


    SmsCodeSendReqDTO convert(AuthSmsSendReqVO reqVO);

//    SmsCodeUseReqDTO convert(AuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

}
