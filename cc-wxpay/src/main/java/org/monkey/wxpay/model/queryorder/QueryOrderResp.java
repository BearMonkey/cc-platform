package org.monkey.wxpay.model.queryorder;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询微信支付订单状态 响应结果
 * @author cc
 */
@Data
public class QueryOrderResp {

    /**
     * 【公众号ID】 公众号ID
     * 选填
     */
    private String appId;

    /**
     * 【直连商户号】 直连商户号
     * 必填
     */
    private String mchId;

    /**
     * 【商户订单号】 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一，详见【商户订单号】。
     * 必填
     */
    private String outTradeNo;

    /**
     * 【微信支付订单号】 微信支付系统生成的订单号。
     * 选填
     */
    private String transactionId;

    /**
     * 【交易类型】 交易类型，枚举值：
     * * JSAPI：公众号支付
     * * NATIVE：扫码支付
     * * APP：APP支付
     * * MICROPAY：付款码支付
     * * MWEB：H5支付
     * * FACEPAY：刷脸支付
     * 选填
     */
    private String tradeType;

    /**
     * 【交易状态】 交易状态，枚举值：
     * * SUCCESS：支付成功
     * * REFUND：转入退款
     * * NOTPAY：未支付
     * * CLOSED：已关闭
     * * REVOKED：已撤销（仅付款码支付会返回）
     * * USERPAYING：用户支付中（仅付款码支付会返回）
     * * PAYERROR：支付失败（仅付款码支付会返回）
     * 必填
     */
    private String tradeState;

    /**
     * 【交易状态描述】 交易状态描述
     * 必填
     */
    private String tradeStateDesc;

    /**
     * 【银行类型】 银行类型，采用字符串类型的银行标识。 银行标识请参考《银行类型对照表》
     * 选填
     */
    private String bankType;

    /**
     * 【附加数据】 附加数据
     * 选填
     */
    private String attach;

    /**
     * 【支付完成时间】 支付完成时间
     * 选填
     */
    private String successTime;

    /**
     * 【支付者】 支付者
     * 选填
     */
    private CommRespPayerInfo payer;

    /**
     * 【订单金额】 订单金额
     * 选填
     */
    private CommRespAmountInfo amount;

    /**
     * 【场景信息】 场景信息
     * 选填
     */
    private CommRespSceneInfo sceneInfo;

    /**
     * 【优惠功能】 优惠功能
     * 选填
     */
    private List<PromotionDetail> promotionDetail = new ArrayList<PromotionDetail>();
}
