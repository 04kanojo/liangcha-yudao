package com.liangcha.system.dataPermission.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据权限类型
 * 语法支持 spel 模板表达式
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {

    /**
     * 全部数据权限
     */
    ALL("1", ""),


    /**
     * 部门数据权限
     */
    DEPT("2", " #{#deptName} = #{#deptId} "),

    /**
     * 指定部门数据权限
     */
    DEPT_DESIGNATE("3", " #{#deptName} IN ( #{@RoleServiceImpl.getDesignateDeptById( #roleId )} ) "),

    /**
     * 部门及以下数据权限
     */
    DEPT_AND_CHILD("4", " #{#deptName} IN ( #{@deptServiceImpl.getChildDeptList( #deptId )} ) "),

    /**
     * 仅本人数据权限
     */
    SELF("5", " #{#userName} = #{#userId} ");

    private final String code;

    /**
     * 语法 采用 spel 模板表达式
     */
    private final String sqlTemplate;

    public static DataScopeTypeEnum findCode(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }
        for (DataScopeTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
