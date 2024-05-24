package org.monkey.wxpay.model.refund;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 退款查询
 * @author cc
 */
@Data
public class RefundSearchResp {
    /**
     * 【子商户号】 子商户的商户号，由微信支付生成并下发。服务商模式下必须传递此参数
     * 选填
     */
    private String subMchid;

    /**
     * 【商户退款单号】 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * 必填
     */
    private String outTradeNo;
}
