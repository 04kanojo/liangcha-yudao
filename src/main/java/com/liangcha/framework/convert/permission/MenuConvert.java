package com.liangcha.framework.convert.permission;

import com.liangcha.server.controller.promission.vo.MenuCreateReqVO;
import com.liangcha.server.controller.promission.vo.MenuRespVO;
import com.liangcha.server.controller.promission.vo.MenuSimpleRespVO;
import com.liangcha.server.controller.promission.vo.MenuUpdateReqVO;
import com.liangcha.system.permission.domain.MenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    List<MenuRespVO> convertList(List<MenuDO> list);

    MenuDO convert(MenuCreateReqVO bean);

    MenuDO convert(MenuUpdateReqVO bean);

    MenuRespVO convert(MenuDO bean);

    List<MenuSimpleRespVO> convertList02(List<MenuDO> list);

}
