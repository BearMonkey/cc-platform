package org.monkey.wxpay.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WxPayConfig {
    /** 商户id */
    @Value("${wx.pay.merchant.mchId:}")
    private String mchId;

    /** 商户apiV3Key */
    @Value("${wx.pay.merchant.apiV3Key:}")
    private String apiV3Key;

    /** 商户私钥文件地址 */
    @Value("${wx.pay.merchant.privateKeyPath:}")
    private String privateKeyPath;

    /** 商户证书序列号 */
    @Value("${wx.pay.merchant.mchSerialNo:}")
    private String serialNo;
}
