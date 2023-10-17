package com.liangcha.system.permission.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
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
@TableName(value = "system_role", autoResultMap = true)
public class RoleDO implements Serializable {

    /**
     * 角色ID
     */
    @TableId
    private Long id;

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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Set<Long> dataScopeDeptIds;

}
