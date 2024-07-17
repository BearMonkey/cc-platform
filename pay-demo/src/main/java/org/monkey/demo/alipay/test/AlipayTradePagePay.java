package org.monkey.demo.alipay.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.AlipayConfig;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.FileItem;
import org.monkey.demo.alipay.common.AlipayConstants;

import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

public class AlipayTradePagePay {

    public static void main(String[] args) throws AlipayApiException {
        testAlipay("" + System.currentTimeMillis(), "648", "米哈游游戏充值");
    }

    private static void testAlipay(String orderNo, String amount, String subject) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(AlipayConstants.URL);
        alipayConfig.setAppId(AlipayConstants.APP_ID);
        alipayConfig.setPrivateKey(AlipayConstants.PRIVATE_KEY);
        alipayConfig.setAlipayPublicKey(AlipayConstants.ALIPAY_PUBLIC_KEY);
        alipayConfig.setFormat(AlipayConstants.FORMAT);
        alipayConfig.setCharset(AlipayConstants.CHARSET);
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE);
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setTotalAmount(amount);
        model.setSubject(subject);
        model.setProductCode(AlipayConstants.PRODUCT_CODE);
        model.setOutTradeNo(orderNo);
        request.setBizModel(model);

        request.setReturnUrl(AlipayConstants.RETURN_URL);

        AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "POST");
        // 如果需要返回GET请求，请使用
        // AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }
}