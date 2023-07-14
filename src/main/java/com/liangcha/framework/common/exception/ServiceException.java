package com.liangcha.framework.common.exception;

import com.liangcha.framework.common.enums.ErrorCodeEnum;
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
    private ErrorCodeEnum errorCodeEnum;
}
