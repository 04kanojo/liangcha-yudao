package com.liangcha.security.pojo.db;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * OAuth2 批准 DO
 * <p>
 * 用户在 sso.vue 界面时，记录接受的 scope 列表
 *
 * @author 凉茶
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_oauth2_approve", autoResultMap = true)
public class OAuth2ApproveDO extends BaseDO {

    /**
     * 编号，数据库自增
     */
    @TableId
    private Long id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 是否接受
     * <p>
     * true - 接受
     * false - 拒绝
     */
    private Boolean approved;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}
