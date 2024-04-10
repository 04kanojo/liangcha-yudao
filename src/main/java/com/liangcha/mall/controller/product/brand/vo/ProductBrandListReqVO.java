package com.liangcha.mall.controller.product.brand.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "管理后台 - 商品品牌分页 Request VO")
@Data
public class ProductBrandListReqVO {

    @ApiModelProperty(value = "品牌名称", example = "苹果")
    private String name;

}
