package com.liangcha.system.module.permission.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户和角色关联
 *
 * @author 凉茶
 */
@TableName("system_user_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleDO extends BaseDO implements Serializable {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 角色 ID
     */
    private Long roleId;

}
