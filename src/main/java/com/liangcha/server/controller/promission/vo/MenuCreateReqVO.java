package com.liangcha.server.controller.promission.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(description = "管理后台 - 菜单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuCreateReqVO extends MenuBaseVO {
}
