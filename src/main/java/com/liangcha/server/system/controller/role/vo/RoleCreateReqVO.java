package com.liangcha.server.system.controller.role.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 角色创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleCreateReqVO extends RoleBaseVO {

}
