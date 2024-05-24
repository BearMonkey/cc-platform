package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.monkey.wxpay.model.common.GoodsDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 */
@Data
public class OrderDetail {

    /**
     * 【订单原价】
     * 1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
     * 2、当订单原价与支付金额不相等，则不享受优惠。
     * 3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
     * 选填
     */
    private Integer costPrice;

    /**
     * 【商品小票ID】 商家小票ID
     * 选填
     */
    private String invoiceId;

    /**
     * 【单品列表】 单品列表信息
     * 条目个数限制：【1，6000】
     * 选填
     */
    List<GoodsDetail> goodsDetails = new ArrayList<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
