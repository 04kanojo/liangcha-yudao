package com.liangcha.framework.convert.log;

import com.liangcha.system.log.domain.LoginLogDO;
import com.liangcha.system.log.dto.LoginLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginLogConvert {

    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    LoginLogDO convert(LoginLogCreateReqDTO bean);

}