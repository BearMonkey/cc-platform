package org.monkey.wxpay.component;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.monkey.wxpay.model.Merchant;
import org.monkey.wxpay.model.addorder.AddOrderExtensionResp;
import org.monkey.wxpay.model.addorder.AddOrderReq;
import org.monkey.wxpay.model.closeorder.CloseOrderReq;
import org.monkey.wxpay.model.queryorder.QueryOrderReq;
import org.monkey.wxpay.model.queryorder.QueryOrderResp;
import org.monkey.wxpay.model.refund.RefundReq;
import org.monkey.wxpay.model.refund.RefundResp;
import org.monkey.wxpay.model.refund.RefundSearchReq;
import org.monkey.wxpay.model.refund.RefundSearchResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
@Data
@Slf4j
public class WxPayer {

    /**
     * 微信支付配置对象，一个商户号只能初始化一个配置，否则会因为重复的下载任务报错
     */
    @Autowired
    private Config wxPayConfig;

    @Autowired
    private JsapiServiceExtension jsapiServiceExtension;

    @Autowired
    private RefundService refundService;

    @Bean(name="wxPayConfig")
    public Config config(Merchant merchant) {
        return new RSAAutoCertificateConfig.Builder()
                .merchantId(merchant.getMchId())
                .privateKeyFromPath(merchant.getPrivateKeyPath())
                .merchantSerialNumber(merchant.getSerialNo())
                .apiV3Key(merchant.getApiV3Key())
                .build();
    }

    @Bean(name = "jsapiService")
    public JsapiServiceExtension getJsapiServiceExtension(Config wxPayConfig) {
        return new JsapiServiceExtension.Builder().config(wxPayConfig).build();
    }

    @Bean(name = "refundService")
    public RefundService getRefundService(Config wxPayConfig) {
        return new RefundService.Builder().config(wxPayConfig).build();
    }

    /**
     * 生成 apiV3秘钥
     * @return 32为随机字符串
     */
    public static String generateApiV3Key() throws NoSuchAlgorithmException {

        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        // 初始化随机数生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, secureRandom); // 256位密钥

        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        // return new String(bytes, StandardCharsets.UTF_8);
        return bytesToHex(keyBytes);
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param bytes 256位字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 微信支付-小程序下单接口
     * @param addOrderReq 下单请求参数封装
     * @return AddOrderResp， 如果返回null，说明下单异常
     */
    public AddOrderExtensionResp addOrder(AddOrderReq addOrderReq) {
        log.info("进入【微信支付-小程序下单】, 商户订单号: {}", addOrderReq.getOutTradeNo());
        try {
            // request.setXxx(val)设置所需参数，具体参数可见Request定义
            PrepayRequest request = new PrepayRequest();

            BeanUtils.copyProperties(addOrderReq, request);

            Amount amount = new Amount();
            amount.setTotal(100);
            request.setAmount(amount);
            request.setDescription("测试商品标题");
            request.setNotifyUrl("https://www.baidu.com");
            request.setOutTradeNo("out_trade_no_001");
            // 调用下单方法，得到应答
            PrepayWithRequestPaymentResponse response = jsapiServiceExtension.prepayWithRequestPayment(request);
            log.info("退出【微信支付-小程序下单】, 商户订单号: {}, 预支付交易会话标识: {}.", addOrderReq.getOutTradeNo(), response.getPackageVal());
            AddOrderExtensionResp addOrderResp = new AddOrderExtensionResp();
            BeanUtils.copyProperties(response, addOrderResp);
            return addOrderResp;
        } catch (Exception e) {
            log.error("【微信支付-小程序下单】异常, ", e);
            return null;
        }
    }

    /**
     * 订单查询
     * @param queryOrderReq 查询参数，
     * @return QueryOrderResp, 如果返回null 说明查询出现异常
     */
    public QueryOrderResp queryOrder(QueryOrderReq queryOrderReq) {
        log.info("【微信支付-订单查询】, 入参: {}", queryOrderReq);
        try {
            Transaction result;
            if (StringUtils.isNotEmpty(queryOrderReq.getOutTradeNo())) {
                QueryOrderByOutTradeNoRequest queryRequest = new QueryOrderByOutTradeNoRequest();
                queryRequest.setOutTradeNo(queryOrderReq.getOutTradeNo());
                queryRequest.setMchid(queryOrderReq.getMchId());
                log.info("【微信支付-订单查询】, 请求参数: {}", queryRequest);
                result = jsapiServiceExtension.queryOrderByOutTradeNo(queryRequest);
                log.info("【微信支付-订单查询】, 响应结果: {}", queryOrderReq);
            } else {
                QueryOrderByIdRequest queryRequest = new QueryOrderByIdRequest();
                queryRequest.setTransactionId(queryOrderReq.getTransactionId());
                queryRequest.setMchid(queryOrderReq.getMchId());
                log.info("【微信支付-订单查询】, 查询参数: {}", queryRequest);
                result = jsapiServiceExtension.queryOrderById(queryRequest);
                log.info("【微信支付-订单查询】, 响应结果: {}", queryOrderReq);
            }
            QueryOrderResp queryOrderResp = new QueryOrderResp();
            BeanUtils.copyProperties(result, queryOrderResp);
            log.info("【微信支付-订单查询】, 微信支付单号/商户订单号: {}, 订单状态: {}.", queryOrderReq.getOutTradeNo(), result.getTradeState());
            return queryOrderResp;
        } catch (ServiceException e) {
            // API返回失败, 例如ORDER_NOT_EXISTS
            log.error("【微信支付-订单查询】异常: ", e);
            log.error("code=[{}], message=[{}]", e.getErrorCode(), e.getErrorMessage());
            log.error("reponse body=[{}]", e.getResponseBody());
        }
        return null;
    }

    /**
     * 关闭订单
     * @param closeOrderReq closeOrderReq
     */
    public void closeOrder(CloseOrderReq closeOrderReq) {
        log.info("【微信支付-关闭订单】, 入参: {}", closeOrderReq);
        try {
            CloseOrderRequest closeRequest = new CloseOrderRequest();
            closeRequest.setMchid(closeOrderReq.getMchId());
            closeRequest.setOutTradeNo(closeOrderReq.getOutTradeNo());
            // 方法没有返回值，意味着成功时API返回204 No Content
            jsapiServiceExtension.closeOrder(closeRequest);
            log.info("【微信支付-关闭订单】, 入参: {}", closeOrderReq);
        } catch (Exception e) {
            log.error("【微信支付-关闭订单】异常: ", e);
        }
    }

    /**
     * 微信支付-退款申请
     * @param refundReq refundReq
     * @return RefundResp 如果发生异常 返回null
     */
    public RefundResp refund(RefundReq refundReq) {
        log.info("【微信支付-退款申请】, 入参: {}", refundReq);
        try {
            // 方法没有返回值，意味着成功时API返回204 No Content
            CreateRequest createRequest = new CreateRequest();
            BeanUtils.copyProperties(refundReq, createRequest);
            log.info("【微信支付-退款申请】, 请求参数: {}", createRequest);
            Refund refund = refundService.create(createRequest);
            log.info("【微信支付-退款申请】, 响应结果: {}", refund);
            RefundResp refundResp = new RefundResp();
            BeanUtils.copyProperties(refund, refundResp);
            return refundResp;
        } catch (Exception e) {
            log.error("【微信支付-退款申请】异常: ", e);
            return null;
        }
    }

    /**
     * 微信支付-退款申请
     * @param refundSearchReq refundSearchReq
     * @return RefundResp 如果发生异常 返回null
     */
    public RefundSearchResp queryRefund(RefundSearchReq refundSearchReq) {
        log.info("【微信支付-查询单笔退款】, 入参: {}", refundSearchReq);
        try {
            // 方法没有返回值，意味着成功时API返回204 No Content
            CreateRequest createRequest = new CreateRequest();
            BeanUtils.copyProperties(refundSearchReq, createRequest);
            log.info("【微信支付-查询单笔退款】, 请求参数: {}", createRequest);
            Refund refund = refundService.create(createRequest);
            log.info("【微信支付-查询单笔退款】, 响应结果: {}", refund);
            RefundSearchResp refundSearchResp = new RefundSearchResp();
            BeanUtils.copyProperties(refund, refundSearchResp);
            return refundSearchResp;
        } catch (Exception e) {
            log.error("【微信支付-查询单笔退款】异常: ", e);
            return null;
        }
    }

}
