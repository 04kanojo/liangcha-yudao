package com.liangcha.common.utils;

import com.liangcha.common.enums.ErrorCodeEnum;
import com.liangcha.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 凉茶
 */
@Slf4j
public class ServiceExceptionUtil {
    public static ServiceException exception(ErrorCodeEnum codeEnum) {
        throw new ServiceException(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static ServiceException exception(ErrorCodeEnum codeEnum, Object... params) {
        String msg = codeEnum.getMsg();
        log.error(msg, params);
        //格式化参数
        String format = StringUtil.format(msg, params);
        throw new ServiceException(codeEnum.getCode(), format);
    }

}
