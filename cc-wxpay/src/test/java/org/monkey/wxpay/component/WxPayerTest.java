package org.monkey.wxpay.component;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class WxPayerTest {

    @Test
    public void generateApiV3Key() throws NoSuchAlgorithmException {
        String apiV3Key = WxPayer.generateApiV3Key();
        System.out.println(apiV3Key);
        Assert.isTrue(apiV3Key.length() == 32, "apiV3Key长度为32: " + apiV3Key);
    }
}