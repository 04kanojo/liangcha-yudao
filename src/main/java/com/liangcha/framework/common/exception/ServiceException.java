package com.liangcha.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑异常
 *
 * @author 凉茶
 */
@Data
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

}
