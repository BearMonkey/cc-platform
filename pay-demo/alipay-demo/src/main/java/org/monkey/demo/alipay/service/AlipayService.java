package org.monkey.demo.alipay.service;

import org.monkey.demo.alipay.dto.OrdOrder;
import org.monkey.demo.alipay.dto.PrepayResp;

/**
 * AlipayService
 *
 * @author Monkey
 * @since 2024/6/30
 */
public interface AlipayService {

    /**
     * 支付宝创建预支付订单
     *
     * @param ordOrder 商户订单信息
     * @return 预支付处理结果
     */
    PrepayResp prepay(String orderId);
}
