package com.liangcha;

import com.liangcha.framework.permission.service.PermissionService;
import com.liangcha.framework.security.pojo.domain.OAuth2ClientDO;
import com.liangcha.system.dao.auth.OAuth2ClientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DataPermissionTest {

    @Resource
    private OAuth2ClientMapper mapper;

    @Resource
    private PermissionService service;

    @Test
    void test() {
        List<OAuth2ClientDO> oAuth2ClientDOS = mapper.selectList(null);
        System.out.println(oAuth2ClientDOS.toString());
    }
}
