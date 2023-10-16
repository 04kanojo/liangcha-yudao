package com.liangcha;

import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.user.dao.AdminUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DataPermissionTest {

    @Resource
    private AdminUserMapper mapper;

    @Test
    void test() {
        List<AdminUserDO> adminUserDOS = mapper.selectList(null);
        System.out.println(adminUserDOS);
    }
}
