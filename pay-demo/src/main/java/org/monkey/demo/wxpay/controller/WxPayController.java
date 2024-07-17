package org.monkey.demo.wxpay.controller;

import com.alibaba.fastjson.JSONObject;
import org.monkey.demo.wxpay.dto.PayResultQueryDto;
import org.monkey.demo.wxpay.dto.PayResultVo;
import org.monkey.demo.wxpay.dto.PrepayReq;
import org.monkey.demo.wxpay.service.WxPayService;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 *
 */
@RestController
@RequestMapping("/wxPay")
@Api(tags = {"微信支付"})
@Slf4j
public class WxPayController {

    @Resource
    private WxPayService wxPayService;

    @ApiOperation(notes = "微信支付-native下单", value = "微信支付-native下单")
    @PostMapping("/nativePay")
    public Result<String> nativePay(@RequestBody PrepayReq req) {
        log.info("[res-business-proc]-[WxPayController]-[nativePay] 接收到的参数:{}", JSONObject.toJSONString(req));
        Result<String> resR = wxPayService.nativePay(req);
        log.info("[res-business-proc]-[WxPayController]-[nativePay] 响应结果:{}", JSONObject.toJSONString(resR));
        return resR;
    }


    @ApiOperation(notes = "微信支付-jsApi下单", value = "微信支付-jsApi下单")
    @PostMapping("/jsApiPay")
    public Result<PrepayWithRequestPaymentResponse> jsApiPay(@RequestBody PrepayReq req) {
        log.info("[res-business-proc]-[WxPayController]-[jsApiPay] 接收到的参数:{}", JSONObject.toJSONString(req));
        Result<PrepayWithRequestPaymentResponse> resR = wxPayService.jsApiPay(req);
        log.info("[res-business-proc]-[WxPayController]-[jsApiPay] 响应结果:{}", JSONObject.toJSONString(resR));
        return resR;
    }

    @ApiOperation(notes = "微信支付-根据商家订单号查询订单信息", value = "微信支付-根据商家订单号查询订单信息")
    @PostMapping("/queryPayResultByOrderNo")
    public Result<PayResultVo> queryPayResultByOrderNo(@RequestBody PayResultQueryDto dto) {
        log.info("[res-business-proc]-[WxPayController]-[queryPayResultByOrderNo] 接收到的参数:{}", JSONObject.toJSONString(dto));
        Result<PayResultVo> resR = wxPayService.queryPayResultByOrderNo(dto);
        log.info("[res-business-proc]-[WxPayController]-[queryPayResultByOrderNo] 响应结果:{}", JSONObject.toJSONString(resR));
        return resR;
    }

    @ApiOperation(notes = "微信支付-回调支付结果", value = "微信支付-回调支付结果")
    @RequestMapping("/payResultCallback")
    public ResponseEntity<Void> payResultCallback(HttpServletRequest request) {
        ResponseEntity<Void> resR = wxPayService.payResultCallback(request);
        log.info("[res-business-proc]-[WxPayController]-[payResultCallback] 响应结果:{}", JSONObject.toJSONString(resR));
        return resR;
    }
}
