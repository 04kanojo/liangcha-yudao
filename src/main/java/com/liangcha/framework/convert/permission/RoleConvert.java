package com.liangcha.framework.convert.permission;

import com.liangcha.server.system.controller.role.vo.RoleCreateReqVO;
import com.liangcha.server.system.controller.role.vo.RoleRespVO;
import com.liangcha.server.system.controller.role.vo.RoleSimpleRespVO;
import com.liangcha.server.system.controller.role.vo.RoleUpdateReqVO;
import com.liangcha.system.permission.domain.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author 凉茶
 */
@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleDO convert(RoleUpdateReqVO bean);

    RoleRespVO convert(RoleDO bean);

    RoleDO convert(RoleCreateReqVO bean);

    List<RoleSimpleRespVO> convertList02(List<RoleDO> list);

//    List<RoleExcelVO> convertList03(List<RoleDO> list);

//    RoleDO convert(RoleCreateReqBO bean);

}
