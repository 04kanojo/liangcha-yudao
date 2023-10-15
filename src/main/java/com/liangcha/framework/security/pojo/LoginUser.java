package com.liangcha.framework.security.pojo;

import com.liangcha.system.domain.permission.RoleDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 登录用户信息
 *
 * @author 凉茶
 */
@Data
@Accessors(chain = true)
public class LoginUser {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 登录用户的角色集合
     */
    private List<RoleDO> roles;

    /**
     * 部门 ID
     */
    private Long deptId;

}
