package org.monkey.wxpay.model.queryorder;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 查询微信支付订单状态 请求体
 * @author cc
 */
@Data
public class QueryOrderReq {
    /**
     * 【商户订单号】 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
     * out_trade_no  transaction_id 二选一
     */
    private String outTradeNo;

    /**
     * 【微信支付订单号】 微信支付系统生成的订单号
     * out_trade_no  transaction_id 二选一
     */
    private String transactionId;

    /**
     * 【直连商户号】 直连商户的商户号，由微信支付生成并下发。
     * 必填
     */
    private String mchId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
