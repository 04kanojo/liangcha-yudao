package com.liangcha.system.domain.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 管理后台的用户 DO
 *
 * @author 凉茶
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "system_users", autoResultMap = true)
public class AdminUserDO {

    /**
     * 用户ID
     */
    @TableId
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * 岗位编号数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Set<Long> postIds;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 帐号状态
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

}