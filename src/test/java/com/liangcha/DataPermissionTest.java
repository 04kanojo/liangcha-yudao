package com.liangcha;

import com.liangcha.system.dao.user.AdminUserMapper;
import com.liangcha.system.domain.auth.AdminUserDO;
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
