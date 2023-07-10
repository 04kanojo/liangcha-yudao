package com.liangcha.convert.service;

import cn.hutool.db.PageResult;
import com.liangcha.security.pojo.db.OAuth2ClientDO;
import com.liangcha.security.pojo.vo.OAuth2ClientCreateReqVO;
import com.liangcha.security.pojo.vo.OAuth2ClientRespVO;
import com.liangcha.security.pojo.vo.OAuth2ClientUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * OAuth2 客户端 Convert
 *
 * @author
 */
@Mapper
public interface OAuth2ClientConvert {

    OAuth2ClientConvert INSTANCE = Mappers.getMapper(OAuth2ClientConvert.class);

    OAuth2ClientDO convert(OAuth2ClientCreateReqVO bean);

    OAuth2ClientDO convert(OAuth2ClientUpdateReqVO bean);

    OAuth2ClientRespVO convert(OAuth2ClientDO bean);

    List<OAuth2ClientRespVO> convertList(List<OAuth2ClientDO> list);

    PageResult<OAuth2ClientRespVO> convertPage(PageResult<OAuth2ClientDO> page);

}
