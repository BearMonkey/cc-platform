package org.monkey.wxpay.model.queryorder;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class CommRespAmountInfo {

    /**
     * 【总金额】 订单总金额，单位为分
     * 选填
     */
    private Integer total;

    /**
     * 【用户支付金额】 用户支付金额，单位为分。（指使用优惠券的情况下，这里等于总金额-优惠券金额）
     * 选填
     */
    private Integer payerTotal;

    /**
     * 【货币类型】 CNY：人民币，境内商户号仅支持人民币。
     * 选填
     */
    private String currency;

    /**
     * 【用户支付币种】 用户支付币种
     * 选填
     */
    private String payerCurrency;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
