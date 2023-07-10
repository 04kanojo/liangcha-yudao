package com.liangcha.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局错误码
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCodeEnum {
    // ========== 基本枚举 ==========
    SUCCESS(200, "成功"),

    SERVICE_ERROR(400, "业务异常"),

    SYSTEM_ERROR(500, "系统异常"),

    UNKNOWN(666, "未知错误"),


    // ========== 权限校验 ==========
    NO_LOGIN(401, "账号未登录"),

    SMALL_AUTHORITY(402, "没有该操作权限"),


    // ========== 请求校验 ==========
    ERR_REQUEST(403, "请求参数不正确"),

    NOT_FOUND(404, "请求未找到"),

    METHOD_NOT_ALLOWED(405, "请求方法错误");

    private final Integer code;
    private final String msg;


}
