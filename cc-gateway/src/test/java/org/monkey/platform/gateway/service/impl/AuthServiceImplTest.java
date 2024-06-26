package org.monkey.platform.gateway.service.impl;

import org.junit.jupiter.api.Test;
import org.monkey.platform.api.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * AuthServiceImplTest
 *
 * @author cc
 * @since 2024/6/25 19:13
 */
@SpringBootTest
public class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authServiceImpl;
    @Test
    public void testCheckAuth() {
        List<String> authList = new ArrayList<>();
        authList.add("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuaWNrTmFtZSI6bnVsbCwiaWQiOjE2LCJ1c2VybmFtZSI6Im1vbmtleSIsInN1YiI6InVzZXItaW5mbyIsImV4cCI6MTcxODE2MjczMSwianRpIjoiNTE3ZGM4YzUtZTE3Yy00MjRiLWI2MDctNTdiZmI4ODkxM2I0In0.n-6gnbqx7Ob0oy_F4PdvFNQ6cyRN_voeN_Y00wz33ho");
        Result<Object> objectResult = authServiceImpl.checkAuth(authList);
        System.out.println(objectResult);
    }
}