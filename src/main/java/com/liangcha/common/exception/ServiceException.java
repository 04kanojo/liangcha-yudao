package com.liangcha.common.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑异常
 *
 * @author 凉茶
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
