package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class AddOrderResp {

    /**
     * 【预支付交易会话标识】 预支付交易会话标识。用于后续接口调用中使用，该值有效期为2小时
     */
    private String prepayId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
