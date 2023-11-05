package com.liangcha.common.utils;

import com.liangcha.common.enums.ErrorCodeEnum;
import com.liangcha.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 凉茶
 */
@Slf4j
public class ServiceExceptionUtil {

    public static final String OR = " 或者 ";

    public static final String AND = " 并且 ";

    public static ServiceException exception(ErrorCodeEnum codeEnum) {
        return new ServiceException(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static ServiceException exception(ErrorCodeEnum codeEnum, Object... params) {
        String msg = codeEnum.getMsg();
        log.error(msg, params);
        //格式化参数
        String format = StringUtil.format(msg, params);
        return new ServiceException(codeEnum.getCode(), format);
    }

    public static ServiceException exception(Integer code, String messagePattern) {
        return new ServiceException(code, messagePattern);
    }

    /**
     * code默认使用第一个错误code
     */
    public static ServiceException exception(ErrorCodeEnum... codeEnum) {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < codeEnum.length; i++) {
            msg.append(codeEnum[i].getMsg());
            if (i != codeEnum.length - 1) {
                msg.append(OR);
            }
        }
        return new ServiceException(codeEnum[0].getCode(), msg.toString());
    }
}
