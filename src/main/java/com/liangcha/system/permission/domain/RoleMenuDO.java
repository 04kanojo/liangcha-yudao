package com.liangcha.system.permission.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色和菜单关联
 *
 * @author 凉茶
 */
@TableName("system_role_menu")
@Data
public class RoleMenuDO {

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
