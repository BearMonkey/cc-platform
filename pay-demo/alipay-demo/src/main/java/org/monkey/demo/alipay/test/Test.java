package org.monkey.demo.alipay.test;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.diagnosis.DiagnosisUtils;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import org.monkey.demo.alipay.common.AlipayConstants;
import org.monkey.platform.common.Constants;

/**
 * Test
 *
 * @author cc
 * @since 2024/6/28 18:19
 */
public class Test {
    public static void main(String[] args) throws Exception{
        AlipayConfig alipayConfig = new AlipayConfig();
        //设置网关地址
        alipayConfig.setServerUrl(AlipayConstants.URL);
        //设置应用ID
        alipayConfig.setAppId(AlipayConstants.APP_ID);
        //设置应用私钥
        alipayConfig.setPrivateKey(AlipayConstants.PRIVATE_KEY);
        //设置请求格式，固定值json
        alipayConfig.setFormat(AlipayConstants.FORMAT);
        //设置字符集
        alipayConfig.setCharset(AlipayConstants.CHARSET);
        //设置签名类型
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE);
        //设置支付宝公钥
        alipayConfig.setAlipayPublicKey(AlipayConstants.ALIPAY_PUBLIC_KEY);
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
        //request.setNotifyUrl(AlipayConstants.NOTIFY_URL);
        //request.setReturnUrl(AlipayConstants.RETURN_URL);
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        //此次只是参数展示，未进行字符串转义，实际情况下请转义
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", System.currentTimeMillis() + "");
        bizContent.put("total_amount", "0.02");
        bizContent.put("subject", "测试商品");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        //bizContent.put("timestamp", "2024-06-28 18:18:18");
        //bizContent.put("primary_industry_name", "T科技/IT软件与服务");
        //bizContent.put("primary_industry_code", "10001/20102");
        //bizContent.put("secondary_industry_code", "10001/20102");
        //bizContent.put("secondary_industry_name", "IT科技/IT软件与服务");
        request.setBizContent(bizContent.toString());
        AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request);

        String pageRedirectionData = response.getBody();
        System.out.println("=============================");
        System.out.println(pageRedirectionData);

        if (response.isSuccess()) {
            //调用成功，则处理业务逻辑
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
             String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
             System.out.println(diagnosisUrl);
        }
    }

    public static void test(String[] args) throws AlipayApiException {
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDanNEVMTiUpYKSPnCY3ZXv3WBwJRKmUm9VwCkcoFYJEvYJLKqBOjyOB0NZxxeGzC3KYkLuzNeILCmgaignWdSkyOU8XTkKvgMsAjsrwM8rkNELCJ9mX7QoNuHN162L2+IIF4HehECZ9EqhonvMh/4+sBmgd5LUKJnD0U3/o0BBHGztzFITqjfmLgDBgjUBGiyo17TXW6v0AXpy10cAI3SFkiL+n0zX/Pa/TZaliJmeEc/sieGjHb2rpe5KOKMIuxGcNXeFe8HI8c4ZIxhNWslYhqfxyvTHq+pPRe9L0PuASbgfLeEgf5xNhar32IrJf3XXzDAQ9WM0vN4/Ef+8ZYBzAgMBAAECggEAJPkYs4jblOhZKPq+eFwhm6vJP65FdTPD+LDDQ0AqzOYUDoErZSfAThUUYv61D+tAY1YzE8g5O6Kcd7D8kYsz15rSil8J4rM4Ph0yfLfjUe7wK/mNW/cbtSFzFyk/BMRvegbfxA6CDoeuh5+/aFwnt9p4uxb+Eju7pc6kbnPhziD8gSMnEzOqZMNmWmUKId2ytTLeaKJCEId9riVsZQ20LPXMxjerkJeyd8M0EpEGo2Q+wDoZO5MMvjSreUK+wlaWIWhAWWozp2jmzfxHOHS3AwhIqrVxQqV1vvpBzH6lrw3ipLu5fvplJxQPdL/RzFEQH+O7ytmJP95i27YidUdnoQKBgQD3dyaGPblz/RWlAu7JmwEH1whaEdYXi/BBnpapT3djb8dBo+oFJtMEoVpGIQSyXGZT9oRlVb/KMpHss4NbEDgBXvIj+KD2XfEjrCe/WwmlmIYPbAva1qhmRZQAnQpfFeOysmyl6B6zlliIqiRFiHXt8qxuthEBOsyyhm3rk9ofJwKBgQDiJu1MMnKHqlLzfgMxl0BNT1NKPv5D39rw0rPasx46CvASUXWV2BM9m72kdIHX0YotDY9iHlYXSh0dEzFDDJ7z+DIxyoJbaN1qbaZTM6KLIpgFvsq+G7mw1a6WqxHyesK8qDJCdM0W38xBazEd5QwzJ9RgSmq1ddXezuIE7abj1QKBgDy/ax2ZGWV3w1B2n9etzVr3Vn2F02CEYb46CBIYB97JGcjrTA9nsHV6hoagpF6EgDosUePuYN5p+5h3mM9J8/WiMR7TcfAIr6FqKXe4CW41OvOeOmrGNyCkA+kCpAEjhnnfgvxXNvIu/hzOZ4kKLqjy4ZFr8FC09nr8tQ8XhHwRAoGARTcYxYtjFjaA8NkObS0XlxHCMiUZePiYb8BtH3BWvfj/BPw9esEJEoAKCL1HyB/vpF5UFfmm67XZNz7aguTn+U9rPWX3g7aSnbI1h510EBVkZc3BC3I84OxlZOOyuH3uAAOSbliFNI0GXLdL7xRdKYL1MwtToXoRHQJAlrWNsIkCgYAlykod3tbFdo9/VP+aMye6YFTUAphM3GxlTux+5pxrTGtHXtUa1hciqzHccvZvzAUOJh3fhx2BR1gcslFZGdAA/3s4y7MmkGd3rwZ9C1eUMioV2kf33RbLsO9MFvgJHXlddk17Z+cw8slKdH1GS8saIbNu4Hh8oq8XxzqIseLNWw==";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA122EAScaIXLzfmPYQOFsMx7RKud7tY+OfEchx+2vPzWZMM1Dpw/N72x8CWtzFB6jdIlFAZRlWWRgvdixfxWl3AQ5c12F3uz1+5iZIfeVANEB/YHlu7E3lZQ3uneAsKCZbv4e/Bf828JpqAUQVZfaF24SLPIyhrrpV5BAm/r56tRPUf6IIG5+UrmtJLuGdvvp81pBr8OEPkj6VskQLc1xptRJFp+lmegMgOIZ8EWmLfY6Cauhdyy9OxwNwkj30T01EcsVgadVzic76+EAx3sNYm4C7fMGfN8vfXsJf9kPRSwK48polMVVA/2upgru/6ou83FqnZXB8WBOTnT1TlX3JQIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
//        alipayConfig.setAppId("9021000138668424");
        //alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setAppId(AlipayConstants.APP_ID);
        alipayConfig.setPrivateKey(AlipayConstants.PRIVATE_KEY);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutRequestNo("CC20240630094811");
        request.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
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