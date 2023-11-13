package com.liangcha.system.module.permission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    /**
     * 内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    private final Integer type;

}
