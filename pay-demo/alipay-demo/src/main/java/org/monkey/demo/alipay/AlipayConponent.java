package org.monkey.demo.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.diagnosis.DiagnosisUtils;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.monkey.demo.alipay.common.AlipayConstants;
import org.monkey.demo.alipay.config.AlipayConf;
import org.monkey.demo.alipay.dto.PrepayResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AlipayConponent
 *
 * @author Monkey
 * @since 2024/6/30
 */
@Component
@Slf4j
public class AlipayConponent {

    @Autowired
    private AlipayConf alipayConf;

    public PrepayResp prepay(String orderNo, String amount, String subject) {
        log.info("Alipay组件收到预支付请求, orderNo={}, amount={}, subject={}.", orderNo, amount, subject);
        PrepayResp prepayResp = new PrepayResp();
        try {
            AlipayConfig alipayConfig = getAlipayConfig();
            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setReturnUrl(alipayConf.getReturnUrl());
            request.setNotifyUrl(alipayConf.getNotifyUrl());


            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
            model.setTotalAmount(amount);
            model.setSubject(subject);
            model.setProductCode(AlipayConstants.PRODUCT_CODE);
            model.setOutTradeNo(orderNo);
            request.setBizModel(model);

            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request, "POST");
            // 如果需要返回GET请求，请使用
            // AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "GET");
            //String pageRedirectionData = response.getBody();
            //System.out.println(pageRedirectionData);
            if (response.isSuccess()) {
                log.info("预支付接口，调用成功");
                log.info(response.getBody());
                prepayResp.setSuccess(true);
                prepayResp.setData(response.getBody());
            } else {
                log.info("预支付接口，调用失败");
                // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
                String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
                System.out.println(diagnosisUrl);
                prepayResp.setSuccess(false);
                prepayResp.setMsg(diagnosisUrl);
            }
        } catch (AlipayApiException e) {
            log.info("预支付异常, ", e);
            prepayResp.setSuccess(false);
            prepayResp.setMsg(e.getMessage());
        }
        log.info("预支付请求结束, orderNo={}, 预支付结果: {}.", orderNo, prepayResp);
        return prepayResp;
    }

    private AlipayConfig getAlipayConfig() {
        AlipayConfig alipayConfig = new AlipayConfig();

        alipayConfig.setAppId(alipayConf.getAppId());
        alipayConfig.setPrivateKey(alipayConf.getPrivateKey());
        alipayConfig.setAlipayPublicKey(alipayConf.getAlipayPublicKey());
        alipayConfig.setServerUrl(AlipayConstants.URL);
        alipayConfig.setFormat(AlipayConstants.FORMAT);
        alipayConfig.setCharset(AlipayConstants.CHARSET);
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE);
        return alipayConfig;
    }
}
