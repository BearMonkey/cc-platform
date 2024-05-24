package org.monkey.wxpay.model.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 商品明细
 * @author cc
 */
@Data
public class GoodsDetail {
    /**
     * 【商户侧商品编码】 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
     * 必填
     */
    private String merchantGoodsId;

    /**
     * 【微信支付商品编码】 微信支付定义的统一商品编号（没有可不传）
     * 选填
     */
    private String wechatpayGoodsId;

    /**
     * 【商品名称】 商品的实际名称
     * 选填
     */
    private String goodsName;

    /**
     * 【商品数量】 用户购买的数量
     * 下单必填
     */
    private Integer quantity;

    /**
     * 【商品单价】 单位为：分。如果商户有优惠，需传输商户优惠后的单价(例如：用户对一笔100元的订单使用了商场发的纸质优惠券100-50，则活动商品的单价应为原单价-50)
     * 必填
     */
    private Integer unitPrice;

    /**
     * 【商品退款金额】 商品退款金额，单位为分
     * 退款时必填
     */
    private Integer refundAmount;

    /**
     * 【商品退货数量】 对应商品的退货数量
     * 退款时必填
     */
    private Integer refundQuantity;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
