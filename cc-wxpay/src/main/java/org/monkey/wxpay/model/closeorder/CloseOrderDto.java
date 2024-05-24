package org.monkey.wxpay.model.closeorder;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 微信关闭订单内部传输对象
 * @author cc
 */
@Data
public class CloseOrderDto {

    /** 【商户订单号】 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。 */
    private String outTradeNo;

    /** 【服务商户号】 服务商户号 */
    private String spMchid;

    /** 【子商户号】 子商户号 */
    private String subMchid;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
