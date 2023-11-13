package com.liangcha.framework.convert.permission;

import com.liangcha.system.controller.promission.vo.MenuCreateReqVO;
import com.liangcha.system.controller.promission.vo.MenuRespVO;
import com.liangcha.system.controller.promission.vo.MenuSimpleRespVO;
import com.liangcha.system.controller.promission.vo.MenuUpdateReqVO;
import com.liangcha.system.module.permission.domain.MenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 凉茶
 */
@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    List<MenuRespVO> convertList(List<MenuDO> list);

    MenuDO convert(MenuCreateReqVO bean);

    MenuDO convert(MenuUpdateReqVO bean);

    MenuRespVO convert(MenuDO bean);

    List<MenuSimpleRespVO> convertList02(List<MenuDO> list);

}
