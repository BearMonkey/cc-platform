package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 小程序下单，并生成调起支付的参数
 * @author cc
 */
@Data
public class AddOrderExtensionResp {

    private String appId;

    /** 时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。 */
    private String timestamp;

    private String nonceStr;

    /** 交易回话标识 */
    private String packageVal;

    /**  */
    private String signType;

    private String paySign;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
