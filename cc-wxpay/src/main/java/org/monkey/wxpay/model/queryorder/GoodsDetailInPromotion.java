package org.monkey.wxpay.model.queryorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 【单品列表】
 * @author cc
 */
@Data
public class GoodsDetailInPromotion {

    /**
     * 【商品编码】 商品编码
     * 必填
     */
    private String goodsId;

    /**
     * 【商品数量】 商品数量
     * 必填
     */
    private Integer quantity;

    /**
     * 【商品单价】 商品单价，单位为分
     * 必填
     */
    private Integer unit_price;

    /**
     * 【商品优惠金额】 商品优惠金额
     * 必填
     */
    private Integer discountAmount;

    /**
     * 【商品备注】 商品备注
     * 选填
     */
    private String goodsRemark;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
