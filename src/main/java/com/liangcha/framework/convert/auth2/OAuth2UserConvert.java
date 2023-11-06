package com.liangcha.framework.convert.auth2;

import com.liangcha.server.system.controller.auth2.vo.OAuth2UserInfoRespVO;
import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.dept.domain.DeptDO;
import com.liangcha.system.dept.domain.PostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface OAuth2UserConvert {

    OAuth2UserConvert INSTANCE = Mappers.getMapper(OAuth2UserConvert.class);

    OAuth2UserInfoRespVO convert(AdminUserDO bean);

    OAuth2UserInfoRespVO.Dept convert(DeptDO dept);

    List<OAuth2UserInfoRespVO.Post> convertList(List<PostDO> list);

}
