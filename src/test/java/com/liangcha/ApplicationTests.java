package com.liangcha;

import com.liangcha.service.user.AdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    private AdminUserService service;

    @Test
    void druidTest() {
    }

}
