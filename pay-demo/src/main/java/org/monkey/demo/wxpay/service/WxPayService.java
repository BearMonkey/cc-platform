package org.monkey.demo.wxpay.service;

import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import org.monkey.demo.wxpay.dto.PayResultQueryDto;
import org.monkey.demo.wxpay.dto.PayResultVo;
import org.monkey.demo.wxpay.dto.PrepayReq;
import org.monkey.platform.common.Result;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface WxPayService {

    /**
     * 微信支付-native下单
     * @param req
     * @return
     */
    Result<String> nativePay(PrepayReq req);

    /**
     * 微信支付-jsApi下单
     * @param req
     * @return
     */
    Result<PrepayWithRequestPaymentResponse> jsApiPay(PrepayReq req);

    /**
     * 微信支付-根据商家订单号查询订单信息
     * @param dto
     * @return
     */
    Result<PayResultVo> queryPayResultByOrderNo(PayResultQueryDto dto);

    /**
     * 微信支付-回调支付结果
     * @param request
     * @return
     */
    ResponseEntity<Void> payResultCallback(HttpServletRequest request);
}
