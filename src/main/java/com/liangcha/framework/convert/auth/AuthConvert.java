package com.liangcha.framework.convert.auth;

import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.server.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.server.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.sms.dto.SmsCodeSendReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 凉茶
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginRespVO convert(LoginUser user);


    SmsCodeSendReqDTO convert(AuthSmsSendReqVO reqVO);
}
