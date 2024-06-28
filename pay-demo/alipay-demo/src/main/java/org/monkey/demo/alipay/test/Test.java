package org.monkey.demo.alipay.test;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.diagnosis.DiagnosisUtils;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
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
        bizContent.put("timestamp", "2024-06-28 18:18:18");
        bizContent.put("primary_industry_name", "T科技/IT软件与服务");
        bizContent.put("primary_industry_code", "10001/20102");
        bizContent.put("secondary_industry_code", "10001/20102");
        bizContent.put("secondary_industry_name", "IT科技/IT软件与服务");
        request.setBizContent(bizContent.toString());
        /*"  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }"*/
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
}
