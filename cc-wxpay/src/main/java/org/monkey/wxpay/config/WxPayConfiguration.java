package org.monkey.wxpay.config;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.refund.RefundService;
import lombok.Data;
import org.monkey.wxpay.model.Merchant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WxPayConfiguration {

    @Bean(name="wxPayConfig")
    public Config config(Merchant merchant) {
        return new RSAAutoCertificateConfig.Builder()
                .merchantId(merchant.getMchId())
                .privateKeyFromPath(merchant.getPrivateKeyPath())
                .merchantSerialNumber(merchant.getSerialNo())
                .apiV3Key(merchant.getApiV3Key())
                .build();
    }

    @Bean(name = "jsapiServiceExtension")
    public JsapiServiceExtension getJsapiServiceExtension(Config wxPayConfig) {
        return new JsapiServiceExtension.Builder().config(wxPayConfig).build();
    }

    @Bean(name = "refundService")
    public RefundService getRefundService(Config wxPayConfig) {
        return new RefundService.Builder().config(wxPayConfig).build();
    }
}
