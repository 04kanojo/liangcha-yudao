package com.liangcha.framework.convert.sms;

import com.liangcha.system.sms.pojo.domain.SmsLogDO;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 凉茶
 */
@Mapper
public interface SmsConvert {

    SmsConvert INSTANCE = Mappers.getMapper(SmsConvert.class);

    SmsLogDO convert(SmsResponse smsResponse);

}
