package com.liangcha.framework.convert.log;

import com.liangcha.system.module.log.domain.LoginLogDO;
import com.liangcha.system.module.log.dto.LoginLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author 凉茶
 */
@Mapper
public interface LoginLogConvert {

    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    LoginLogDO convert(LoginLogCreateReqDTO bean);

}
