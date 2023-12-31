package com.liangcha.system.module.permission.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import com.liangcha.framework.mybatisplus.type.JsonLongSetTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色 DO
 *
 * @author 凉茶
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_role", autoResultMap = true)
public class RoleDO extends BaseDO implements Serializable {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String code;

    /**
     * 角色排序
     */
    private Integer sort;

    /**
     * 角色状态
     */
    private Integer status;

    /**
     * 角色类型
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据范围
     */
    private Integer dataScope;

    /**
     * 数据范围(指定部门数组)
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> dataScopeDeptIds;

}
