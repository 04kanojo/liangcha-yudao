package com.liangcha.framework.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举类
 * <p>
 * 用于实现数据级别的权限
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    ALL(1), // 全部数据权限

    DEPT_CUSTOM(2), // 指定部门数据权限
    DEPT_ONLY(3), // 部门数据权限
    DEPT_AND_CHILD(4), // 部门及以下数据权限

    SELF(5); // 仅本人数据权限

    /**
     * 范围
     */
    private final Integer scope;

}
