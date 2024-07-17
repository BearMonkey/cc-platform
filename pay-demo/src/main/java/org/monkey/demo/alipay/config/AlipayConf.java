package org.monkey.demo.alipay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AlipayConfig
 *
 * @author Monkey
 * @since 2024/6/30
 */
@Data
@Component
@ConfigurationProperties(prefix = "org.monkey.cc.platform.pay.alipay")
public class AlipayConf {
    private String appId;

    private String privateKey;

    private String alipayPublicKey;

    private String notifyUrl;

    private String returnUrl;
}
