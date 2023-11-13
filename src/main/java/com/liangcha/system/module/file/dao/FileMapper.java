package com.liangcha.system.module.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.module.file.domain.FileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作 Mapper
 *
 * @author 凉茶
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {

}
