package org.monkey.wxpay.model.closeorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 下单接口请求
 * @author cc
 */
@Data
public class CloseOrderReq {

    /**
     * 【商户订单号】 商户系统内部订单号，可以是数字、大小写字母_-*的任意组合，且在同一个商户号下唯一
     * 必填
     */
    private String outTradeNo;

    /**
     * 【直连商户号】 直连商户号
     * 必填
     */
    private String mchId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
