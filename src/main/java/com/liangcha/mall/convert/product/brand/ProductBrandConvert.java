package com.liangcha.mall.convert.product.brand;

import cn.hutool.db.PageResult;
import com.liangcha.mall.controller.product.brand.vo.ProductBrandCreateReqVO;
import com.liangcha.mall.controller.product.brand.vo.ProductBrandRespVO;
import com.liangcha.mall.controller.product.brand.vo.ProductBrandUpdateReqVO;
import com.liangcha.mall.product.brand.domain.ProductBrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 品牌 Convert
 *
 * @author 凉茶
 */
@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrandDO convert(ProductBrandCreateReqVO bean);

    ProductBrandDO convert(ProductBrandUpdateReqVO bean);

    ProductBrandRespVO convert(ProductBrandDO bean);

//    List<ProductBrandSimpleRespVO> convertList1(List<ProductBrandDO> list);

    List<ProductBrandRespVO> convertList(List<ProductBrandDO> list);

    PageResult<ProductBrandRespVO> convertPage(PageResult<ProductBrandDO> page);

}
