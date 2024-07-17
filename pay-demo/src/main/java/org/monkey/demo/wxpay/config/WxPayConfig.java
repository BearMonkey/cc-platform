package org.monkey.demo.wxpay.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author lgf
 * @Date 2024-07-05 17:41
 * @PackageName:com.szmsd.proc.pay.wxpay.config
 * @ClassName: WxPayConfig
 * @Description:
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "org.monkey.cc.platform.pay.wxpay")
public class WxPayConfig {

    @ApiModelProperty(value = "商户号")
    private String merchantId;

    @ApiModelProperty(value = "商户API私钥路径")
    private String privateKeyPath;

    @ApiModelProperty(value = "商户证书序列号")
    private String merchantSerialNumber;

    @ApiModelProperty(value = "商户APIV3密钥")
    private String apiV3Key;

    @ApiModelProperty(value = "支付结果回调通知url")
    private String payNotifyUrl;

    @ApiModelProperty(value = "公众号appid")
    private String appId;

    @ApiModelProperty(value = "直连商户号")
    private String mchId;
}
