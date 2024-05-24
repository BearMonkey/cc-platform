package org.monkey.wxpay.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 商户模型
 * @author cc
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx.pay.merchant")
public class Merchant {
    /** 商户id */
    private String mchId;

    /** 商户apiV3Key */
    private String apiV3Key;

    /** 商户私钥文件地址 */
    private String privateKeyPath;

    /** 商户证书序列号 */
    private String serialNo;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
