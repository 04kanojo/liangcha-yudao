package com.liangcha.mall.product.brand.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.mall.controller.product.brand.vo.ProductBrandListReqVO;
import com.liangcha.mall.product.brand.domain.ProductBrandDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductBrandMapper extends BaseMapper<ProductBrandDO> {

//    default PageResult<ProductBrandDO> selectPage(ProductBrandPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<ProductBrandDO>()
//                .likeIfPresent(ProductBrandDO::getName, reqVO.getName())
//                .eqIfPresent(ProductBrandDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(ProductBrandDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(ProductBrandDO::getId));
//    }


    default List<ProductBrandDO> selectList(ProductBrandListReqVO reqVO) {
        return selectList(new LambdaQueryWrapper<ProductBrandDO>()
                .like(ProductBrandDO::getName, reqVO.getName()));
    }

    default ProductBrandDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<ProductBrandDO>()
                .eq(ProductBrandDO::getName, name));
    }
//
//    default List<ProductBrandDO> selectListByStatus(Integer status) {
//        return selectList(ProductBrandDO::getStatus, status);
//    }
}
