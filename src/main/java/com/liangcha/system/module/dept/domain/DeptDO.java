package com.liangcha.system.module.dept.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门表
 *
 * @author 凉茶
 */
@TableName("system_dept")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptDO extends BaseDO {

    /**
     * 部门ID
     */
    @TableId
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private Long leaderUserId;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态
     */
    private Integer status;

}
