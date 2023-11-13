package com.liangcha.system.module.log.service;

import com.liangcha.framework.convert.log.LoginLogConvert;
import com.liangcha.system.module.log.dao.LoginLogMapper;
import com.liangcha.system.module.log.domain.LoginLogDO;
import com.liangcha.system.module.log.dto.LoginLogCreateReqDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录日志 Service 实现
 *
 * @author 凉茶
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }
}
