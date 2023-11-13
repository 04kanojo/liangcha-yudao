package com.liangcha.framework.convert.log;

import com.liangcha.system.module.log.domain.OperateLogDO;
import com.liangcha.system.module.log.dto.OperateLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author 凉茶
 */
@Mapper
public interface OperateLogConvert {

    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    OperateLogDO convert(OperateLogCreateReqDTO operateLogCreateReqDTO);

    OperateLogCreateReqDTO convert(OperateLogDO operateLog);

}
