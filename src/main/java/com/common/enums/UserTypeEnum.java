package com.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局用户类型枚举
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    MEMBER(1, "会员"),

    ADMIN(2, "管理员");

    /**
     * 类型
     */
    private final Integer code;

    /**
     * 类型名
     */
    private final String name;

}
