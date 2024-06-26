package org.monkey.platform.auth.controller;

import org.junit.jupiter.api.Test;
import org.monkey.platform.api.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AuthControllerTest
 *
 * @author cc
 * @since 2024/6/25 17:49
 */
@SpringBootTest
class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    public void testCheckToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJuaWNrTmFtZSI6bnVsbCwiaWQiOjE2LCJ1c2VybmFtZSI6Im1vbmtleSIsInN1YiI6InVzZ" +
                "XItaW5mbyIsImV4cCI6MTcxODE2MjczMSwianRpIjoiNTE3ZGM4YzUtZTE3Yy00MjRiLWI2MDctNTdi" +
                "ZmI4ODkxM2I0In0.n-6gnbqx7Ob0oy_F4PdvFNQ6cyRN_voeN_Y00wz33ho";
        try {
            Result<Object> result = authController.checkToken(token);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}