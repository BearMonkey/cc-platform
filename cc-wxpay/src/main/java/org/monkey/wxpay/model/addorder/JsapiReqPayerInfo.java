package org.monkey.wxpay.model.addorder;

import lombok.Data;

@Data
public class JsapiReqPayerInfo {
    /**
     * 【用户标识】 用户在普通商户AppID下的唯一标识。 下单前需获取到用户的OpenID
     * 必填
     */
    private String openid;
}
