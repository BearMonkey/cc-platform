package org.monkey.demo.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import org.monkey.demo.wxpay.config.WxPayConfig;
import org.monkey.demo.wxpay.dto.PayResultQueryDto;
import org.monkey.demo.wxpay.dto.PayResultVo;
import org.monkey.demo.wxpay.dto.PrepayReq;
import org.monkey.demo.wxpay.dto.TaskDto;
import org.monkey.demo.wxpay.service.WxPayService;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.model.TransactionAmount;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByOutTradeNoRequest;
import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    @Resource
    private WxPayConfig wxPayConfig;

    public RSAAutoCertificateConfig rsaAutoCertificateConfig;

    @Autowired
    private ArrayBlockingQueue<TaskDto> taskQueue;

    @PostConstruct
    public void initConfig() {
        try {
            rsaAutoCertificateConfig = new RSAAutoCertificateConfig.Builder()
                    .merchantId(wxPayConfig.getMerchantId())
                    .privateKeyFromPath(wxPayConfig.getPrivateKeyPath())
                    .merchantSerialNumber(wxPayConfig.getMerchantSerialNumber())
                    .apiV3Key(wxPayConfig.getApiV3Key())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[res-business-proc]-[WxPayServiceImpl]-[initConfig] 初始化异常", e);
        }
    }


    @Override
    //@Transactional(rollbackFor = Exception.class)
    public Result<String> nativePay(PrepayReq req) {
        //必填参数校验
        /*String errorMsg = checkReq(req);
        if (StringUtil.isNotBlank(errorMsg)) {
            return Result.fail(errorMsg);
        }
        int amount = Integer.parseInt(req.getAmount());

        Result<String> storePrePay = storePrePay(req);
        if (storePrePay.getCode() != 200) {
            return Result.fail(storePrePay.getMsg());
        }

        //调用微信的支付逻辑
        try {
            NativePayService service = new NativePayService.Builder()
                    .config(rsaAutoCertificateConfig).build();
            PrepayRequest request = new PrepayRequest();
            Amount amountDto = new Amount();
            amountDto.setTotal(amount);
            request.setAmount(amountDto);
            request.setAppid(wxPayConfig.getAppId());
            request.setMchid(wxPayConfig.getMchId());
            request.setDescription(req.getSubject());
            request.setNotifyUrl(wxPayConfig.getPayNotifyUrl());
            request.setOutTradeNo(req.getOrderNo());

            log.info("[res-business-proc]-[WxPayServiceImpl]-[nativePay] 调用微信 请求参数:{}", JSON.toJSONString(request));
            PrepayResponse response = service.prepay(request);
            log.info("[res-business-proc]-[WxPayServiceImpl]-[nativePay] 调用微信 响应结果:{}", JSON.toJSONString(response));
            if (response != null) {
                return R.ok(response.getCodeUrl());
            }
            return R.failed("预下单返回结果为空");

        } catch (Exception e) {
            e.printStackTrace();
            log.info("[res-business-proc]-[WxPayServiceImpl]-[nativePay] 调用微信 出现异常:", e);
            return R.failed("预下单失败:" + e.getMessage());
        }*/
        return Result.success();

    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public Result<PrepayWithRequestPaymentResponse> jsApiPay(PrepayReq req) {
        //必填参数校验
        /*String errorMsg = checkReq(req);
        if (StringUtil.isNotBlank(errorMsg)) {
            return R.failed(errorMsg);
        }
        if (StringUtils.isEmpty(req.getOpenId())) {
            return R.failed("openId不能为空");
        }
        int amount = Integer.parseInt(req.getAmount());
        R<String> storePrePay = storePrePay(req);
        if (storePrePay.getCode() != 200) {
            return R.failed(storePrePay.getMsg());
        }

        //调用微信的支付逻辑
        try {
            JsapiServiceExtension service = new JsapiServiceExtension.Builder()
                    .config(rsaAutoCertificateConfig)
                    .signType("RSA")
                    .build();

            com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest request = new com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest();
            com.wechat.pay.java.service.payments.jsapi.model.Amount amountDto = new com.wechat.pay.java.service.payments.jsapi.model.Amount();
            amountDto.setTotal(amount);
            request.setAmount(amountDto);

            Payer payer = new Payer();
            payer.setOpenid(req.getOpenId());
            request.setPayer(payer);

            request.setAppid(wxPayConfig.getAppId());
            request.setMchid(wxPayConfig.getMchId());
            request.setDescription(req.getSubject());
            request.setNotifyUrl(wxPayConfig.getPayNotifyUrl());
            request.setOutTradeNo(req.getOrderNo());

            log.info("[res-business-proc]-[WxPayServiceImpl]-[jsApiPay] 调用微信 请求参数:{}", JSON.toJSONString(request));
            com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(request);
            log.info("[res-business-proc]-[WxPayServiceImpl]-[jsApiPay] 调用微信 响应结果:{}", JSON.toJSONString(response));
            if (response != null) {
                return R.ok(response);
            }
            return R.failed("预下单返回结果为空");

        } catch (Exception e) {
            e.printStackTrace();
            log.info("[res-business-proc]-[WxPayServiceImpl]-[nativePay] 调用微信 出现异常:", e);
            return R.failed("预下单失败:" + e.getMessage());
        }*/
        return Result.success();
    }

    @Override
    public Result<PayResultVo> queryPayResultByOrderNo(PayResultQueryDto dto) {
        /*NativePayService service = new NativePayService.Builder()
                .config(rsaAutoCertificateConfig).build();
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid(wxPayConfig.getMchId());
        request.setOutTradeNo(dto.getOrderNo());
        Transaction transaction = null;
        try {
            log.info("[res-business-proc]-[WxPayServiceImpl]-[queryPayResultByOrderNo] 调用微信 请求参数:{}", JSON.toJSONString(request));
            transaction = service.queryOrderByOutTradeNo(request);
            log.info("[res-business-proc]-[WxPayServiceImpl]-[queryPayResultByOrderNo] 调用微信 响应结果:{}", JSON.toJSONString(transaction));

            PayResultVo payResultVo = new PayResultVo();
            payResultVo.setOrderNo(transaction.getOutTradeNo());
            TransactionAmount transactionAmount = transaction.getAmount();
            if (transactionAmount != null) {
                payResultVo.setAmount(transactionAmount.getTotal());
            }
            payResultVo.setSuccessTime(DateUtils.parseDate(transaction.getSuccessTime()));
            payResultVo.setTransactionId(transaction.getTransactionId());
            payResultVo.setTradeState(transaction.getTradeStateDesc());
            payResultVo.setTradeStateDesc(transaction.getTradeStateDesc());
            payResultVo.setTradeType(null != transaction.getTradeType() ? transaction.getTradeType().name() : null);
            return R.ok(payResultVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[res-business-proc]-[WxPayServiceImpl]-[queryPayResultByOrderNo] 调用微信 失败:", e);
            return Result.fail("查询失败:" + e.getMessage());
        }*/
        return Result.success();
    }

    @Override
    public ResponseEntity<Void> payResultCallback(HttpServletRequest request) {
        //读取body和对应的请求头
        String requestBodyStr = getRequestBodyStr(request);
        String wechatSignature = request.getHeader("Wechatpay-Signature");
        String wechatPaySerial = request.getHeader("Wechatpay-Serial");
        String wechatPayNonce = request.getHeader("Wechatpay-Nonce");
        String wechatTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wechatSignatureType = request.getHeader("Wechatpay-Signature-Type");

        // 构造 RequestParam
        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(wechatPaySerial)
                .nonce(wechatPayNonce)
                .signature(wechatSignature)
                .timestamp(wechatTimestamp)
                .body(requestBodyStr)
                .build();
        log.info("[res-business-proc]-[WxPayServiceImpl]-[payResultCallback] 接收到的参数{}", JSON.toJSONString(requestParam));

        NotificationParser parser = new NotificationParser(rsaAutoCertificateConfig);
        Transaction transaction = null;
        try {
            transaction = parser.parse(requestParam, Transaction.class);
            log.info("[res-business-proc]-[WxPayServiceImpl]-[payResultCallback] 解析后的结果{}", JSON.toJSONString(transaction));
            String orderNo = transaction.getOutTradeNo();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取request内容文本流
     *
     * @param request
     * @return
     */
    public static String getRequestBodyStr(HttpServletRequest request) {
        String bodyStr = "";
        try {
            BufferedInputStream buffer = new BufferedInputStream(
                    request.getInputStream());

            int cLen = 0, bufLen = 0;
            byte[] result = new byte[4096];
            byte[] buf = new byte[4096];

            while ((bufLen = buffer.read(buf)) > 0) {
                if (bufLen > result.length - cLen) {
                    byte[] tmp = new byte[result.length + 4096];
                    System.arraycopy(result, 0, tmp, 0, cLen);
                    result = tmp;
                }
                System.arraycopy(buf, 0, result, cLen, bufLen);
                cLen += bufLen;
            }

            bodyStr = new String(Arrays.copyOf(
                    result, cLen), StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(bodyStr)) {
                log.info("接口调用" + "：请求体文本流为空");
                return "";
            }

        } catch (Exception e) {
            log.error("接口调用" + "：请求内容体解析异常:", e);
            e.printStackTrace();
        }
        return bodyStr;
    }
}
