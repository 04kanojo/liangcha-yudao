package com.liangcha.mall.controller.product.brand.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品品牌 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductBrandBaseVO {

    @ApiModelProperty("品牌名称")
    @NotNull(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty("品牌图片")
    @NotNull(message = "品牌图片不能为空")
    private String picUrl;

    @ApiModelProperty(value = "品牌排序", example = "1")
    @NotNull(message = "品牌排序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "品牌描述", example = "描述")
    private String description;

    @ApiModelProperty(value = "状态", example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
