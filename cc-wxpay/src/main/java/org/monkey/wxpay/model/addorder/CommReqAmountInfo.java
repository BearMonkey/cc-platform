package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 【订单金额】 订单金额信息
 * @author cc
 */
@Data
public class CommReqAmountInfo {

    /**
     * 【总金额】 订单总金额，单位为分。
     * 必填
     */
    private Integer total;

    /**
     * 【货币类型】 CNY：人民币，境内商户号仅支持人民币。
     * 选填
     */
    private String currency;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
