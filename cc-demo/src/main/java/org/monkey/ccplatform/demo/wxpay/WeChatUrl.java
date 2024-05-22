package org.monkey.ccplatform.demo.wxpay;

/**
 * WeChat 支付接口常量定义
 *
 * @author cc
 */
public class WeChatUrl {
    /**
     * 主域名
     */
    public static final String DOMAIN_NAME = "https://api.mch.weixin.qq.com";

    /**
     * 备用域名，未使用
     */
    public static final String DOMAIN_NAME_BAK = "https://api2.mch.weixin.qq.com";

    /**
     * WeChat 下单uri
     * POST /v3/pay/transactions/jsapi
     */
    public static final String WECHAT_ADD_ORDER_URI = "/v3/pay/transactions/jsapi";

    // WeChat 调起支付接口 由前台js完成，无uri

    // WeChat 支付通知(该接口由微信回调)

    /**
     * WeChat `微信支付订单号`查询订单 uri
     * GET /v3/pay/transactions/id/{transaction_id}
     */
    public static final String WECHAT_GET_ORDER_WX_URI = "/v3/pay/transactions/id/";

    /**
     * WeChat `商户订单号`查询订单 uri
     * GET /v3/pay/transactions/out-trade-no/{out_trade_no}
     */
    public static final String WECHAT_GET_ORDER_MERCHANT_URI = "/v3/pay/transactions/out-trade-no/";

    /**
     * WeChat 关闭订单 uri 前缀 （完整uri=前缀 + 商户订单号 + 后缀）
     * POST  /v3/pay/transactions/out-trade-no/{out_trade_no}/close
     */
    public static final String WECHAT_CLOSE_ORDER_URI_PREFIX = "/v3/pay/transactions/out-trade-no/";

    /**
     * WeChat 关闭订单 uri 后缀 （完整uri=前缀 + 商户订单号 + 后缀）
     * POST  /v3/pay/transactions/out-trade-no/{out_trade_no}/close
     */
    public static final String WECHAT_CLOSE_ORDER_URI_SUFFIX = "/close";

    /**
     * WeChat 退款申请 uri
     * POST /v3/refund/domestic/refunds
     */
    public static final String WECHAT_REFUNDS_URI = "/v3/refund/domestic/refunds";

    /**
     * WeChat 查询单笔退款（通过商户退款单号） uri
     * GET /v3/refund/domestic/refunds/{out_refund_no}
     */
    public static final String WECHAT_GET_REFUNDS_URI = "/v3/refund/domestic/refunds/";

    // 退款结果通知，提供给微信的回调接口

    /**
     * WeChat 申请交易账单 uri
     * GET /v3/bill/tradebill
     */
    public static final String WECHAT_GET_TRADEBILL_URI = "/v3/bill/tradebill";

    /**
     * WeChat 申请资金账单 uri
     * GET /v3/bill/fundflowbill
     */
    public static final String WECHAT_GET_FUNDFLOWBILL_URI = "/v3/bill/fundflowbill";

    // 下载账单，申请账单的接口返回结果中包含download_url字段，通过该字段结果下载
}
