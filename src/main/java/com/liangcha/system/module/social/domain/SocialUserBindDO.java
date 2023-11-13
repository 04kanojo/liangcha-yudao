package com.liangcha.system.module.social.domain;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import com.liangcha.system.module.user.enums.UserTypeEnum;
import lombok.*;

import java.io.Serializable;

/**
 * 社交用户的绑定
 * 即 {@link SocialUserDO} 与 UserDO 的关联表
 *
 * @author 凉茶
 */
@TableName(value = "system_social_user_bind", autoResultMap = true)
@KeySequence("system_social_user_bind_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserBindDO extends BaseDO implements Serializable {

    /**
     * 关联的用户编号
     * <p>
     * 关联 UserDO 的编号
     */
    private Long userId;

    /**
     * 用户类型
     * <p>
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;

    /**
     * 社交平台的用户编号
     * <p>
     * 关联 {@link SocialUserDO#getId()}
     */
    private Long socialUserId;

    /**
     * 社交平台的类型
     * <p>
     * 冗余 {@link SocialUserDO#getType()}
     */
    private Integer socialType;

}
