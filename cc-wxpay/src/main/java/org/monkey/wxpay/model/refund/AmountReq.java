package org.monkey.wxpay.model.refund;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class AmountReq {
    /**
     * 【退款金额】 退款金额，单位为分，只能为整数，不能超过原订单支付金额。
     * 必填
     */
    private String refund;

    /**
     * 【退款出资账户及金额】 退款需要从指定账户出资时，传递此参数指定出资金额（币种的最小单位，只能为整数）。
     * 选填
     */
    private String from;

    /**
     * 【原订单金额】 原支付交易的订单总金额，单位为分，只能为整数。
     * 必填
     */
    private String total;

    /**
     * 【退款币种】 符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
     * 必填
     */
    private String currency;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
