package com.liangcha.framework.common.pojo;

import com.liangcha.framework.common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回
 *
 * @param <T> 数据泛型
 * @author 凉茶
 */
@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 错误提示
     */
    private String msg;

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ErrorCodeEnum.SUCCESS.getCode(), data, ErrorCodeEnum.SUCCESS.getMsg());
    }

    public static <T> CommonResult<T> error(ErrorCodeEnum enumType) {
        return new CommonResult<>(enumType.getCode(), enumType.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        return new CommonResult<>(code, msg);
    }
}
