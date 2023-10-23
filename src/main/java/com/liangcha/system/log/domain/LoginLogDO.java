package com.liangcha.system.log.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import com.liangcha.system.auth.enums.LoginLogTypeEnum;
import com.liangcha.system.user.enums.UserTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录日志表
 * <p>
 * 注意，包括登录和登出两种行为
 *
 * @author 芋道源码
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_login_log")
public class LoginLogDO extends BaseDO {

    /**
     * 日志主键
     */
    private Long id;

    /**
     * 日志类型
     * <p>
     * 枚举 {@link LoginLogTypeEnum}
     */
    private Integer logType;

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     * <p>
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;

    /**
     * 用户账号
     * <p>
     * 冗余，因为账号可以变更
     */
    private String username;

    /**
     * 登录结果
     */
    private Integer result;

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * 浏览器 UA
     */
    private String userAgent;

}
