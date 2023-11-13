package com.liangcha.system.module.permission.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色和菜单关联
 *
 * @author 凉茶
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_role_menu")
public class RoleMenuDO extends BaseDO implements Serializable {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

}
