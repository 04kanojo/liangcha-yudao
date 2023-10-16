package com.liangcha.server.controller.role.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 角色 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 *
 * @author 凉茶
 */
@Data
public class RoleBaseVO {

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String name;

    @ApiModelProperty("角色标志")
    @NotBlank(message = "角色标志不能为空")
    @Size(max = 100, message = "角色标志长度不能超过100个字符")
    private String code;

    @ApiModelProperty("显示顺序")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;

}
