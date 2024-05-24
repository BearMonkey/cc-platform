package org.monkey.wxpay.model.queryorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 【优惠功能】 优惠功能
 * @author cc
 */
@Data
public class PromotionDetail {

    /**
     * 【券ID】 券ID
     * 选填
     */
    private String couponId;

    /**
     * 【优惠名称】 优惠名称
     * 选填
     */
    private String name;

    /**
     * 【优惠范围】 优惠范围，枚举值：
     * GLOBAL：全场代金券
     * SINGLE：单品优惠
     * 选填
     */
    private String scope;

    /**
     * 【优惠类型】 优惠类型，枚举值：
     * CASH：充值型代金券
     * NOCASH：免充值型代金券
     * 选填
     */
    private String type;

    /**
     * 【优惠券面额】 优惠券面额
     * 选填
     */
    private Integer amount;

    /**
     * 【活动ID】 活动ID，批次ID
     * 选填
     */
    private String stockId;

    /**
     * 【微信出资】 微信出资，单位为分
     * 选填
     */
    private Integer wechatpayContribute;

    /**
     * 【商户出资】 商户出资，单位为分
     * 选填
     */
    private Integer merchantContribute;

    /**
     * 【其他出资】 其他出资，单位为分
     * 选填
     */
    private Integer otherContribute;

    /**
     * 【优惠币种】 CNY：人民币，境内商户号仅支持人民币。
     * 选填
     */
    private String currency;

    /**
     * 【单品列表】
     * 选填
     */
    private List<GoodsDetailInPromotion> goodsDetail = new ArrayList<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
