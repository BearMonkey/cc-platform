package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class StoreInfo {

    /**
     * 【门店编号】 商户侧门店编号
     * 必填
     */
    private String id;

    /**
     * 【门店名称】 商户侧门店名称
     * 选填
     */
    private String name;

    /**
     * 【地区编码】 地区编码，详细请见:
     * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/terms_definition/chapter1_1_3.shtml#part-5">省市区编号对照表</a>
     * 选填
     */
    private String areaCode;

    /**
     * 【详细地址】 详细的商户门店地址
     * 选填
     */
    private String address;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
