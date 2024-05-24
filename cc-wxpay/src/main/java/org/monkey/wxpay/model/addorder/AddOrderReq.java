package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class AddOrderReq {

    /**
     * 【服务商应用ID】 由微信生成的应用ID，全局唯一。请求基础下单接口时请注意AppID的应用属性，例如公众号场景下，需使用应用属性为公众号的服务号AppID
     * 必填
     */
    private String spAppid;

    /**
     * 【服务商户号】 服务商户号，由微信支付生成并下发
     * 必填
     */
    private String spMchid;

    /**
     * 【子商户/二级商户应用ID】 子商户/二级商户在开放平台申请的应用AppID，全局唯一。请求基础下单接口时请注意AppID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID
     * 若sub_openid有传的情况下，sub_appid必填，且sub_appid需与sub_openid对应
     * 必填
     */
    private String subAppid;

    /**
     * 【服务商户号】 服务商户号，由微信支付生成并下发
     * 必填
     */
    private String subMchid;

    /**
     * 【商品描述】 商品描述
     * 必填
     */
    private String description;

    /**
     * 【商户订单号】 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
     * 必填
     */
    private String outTradeNo;

    /**
     * 【交易结束时间】 订单失效时间，遵循rfc3339标准格式，
     * 格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
     * HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
     * 选填
     */
    private String timeExpire;

    /**
     * 【附加数据】 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段。
     * 选填
     */
    private String attach;

    /**
     * 【通知地址】 异步接收微信支付结果通知的回调地址，通知URL必须为外网可访问的URL，不能携带参数。 公网域名必须为HTTPS，
     * 如果是走专线接入，使用专线NAT IP或者私有回调域名可使用HTTP
     * 必填
     */
    private String notifyUrl;

    /**
     * 【订单优惠标记】 订单优惠标记
     * 选填
     */
    private String goods_tag;

    /**
     * 【结算信息】 结算信息
     * 选填
     */
    private PartnerSettleInfo settleInfo;

    /**
     * 【电子发票入口开放标识】 传入true时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效。
     * true：是
     * false：否
     * 选填
     */
    private boolean supportFapiao;

    /**
     * 【电子发票入口开放标识】 传入true时，支付成功消息和支付详情页将出现开票入口。
     * 需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效。
     * true：是
     * false：否
     * 选填
     */
    private CommReqAmountInfo amount;

    /**
     * 【支付者 】 支付者信息。
     */
    private JsapiReqPayerInfo payer;

    /**
     * 【优惠功能】 优惠功能
     * 选填
     */
    private OrderDetail detail;

    /**
     * 【场景信息】 支付场景描述
     * 选填
     */
    private CommReqSceneInfo sceneInfo;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
