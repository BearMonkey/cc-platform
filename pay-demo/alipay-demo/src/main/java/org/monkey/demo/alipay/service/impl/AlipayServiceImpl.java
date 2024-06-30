package org.monkey.demo.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import lombok.extern.slf4j.Slf4j;
import org.monkey.demo.alipay.AlipayConponent;
import org.monkey.demo.alipay.dto.GoodsInfo;
import org.monkey.demo.alipay.dto.OrdOrder;
import org.monkey.demo.alipay.dto.PrepayResp;
import org.monkey.demo.alipay.mapper.OrderMapper;
import org.monkey.demo.alipay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * AlipayServiceImpl
 *
 * @author Monkey
 * @since 2024/6/30
 */
@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayConponent alipayConponent;

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public PrepayResp prepay(String orderId) {
        PrepayResp prepayResp = new PrepayResp();
        OrdOrder ordOrder = orderMapper.findOrder(orderId);
        if (null == ordOrder) {
            prepayResp.setSuccess(false);
            prepayResp.setMsg("订单不存在, orderId=" + orderId);
            return prepayResp;
        }
        List<GoodsInfo> goodsInfoList = ordOrder.getGoodsInfoList();
        if (CollectionUtils.isEmpty(goodsInfoList)) {
            prepayResp.setSuccess(false);
            prepayResp.setMsg("订单下没有关联商品: " + orderId);
            return prepayResp;
        }
        GoodsInfo goodsInfo = goodsInfoList.get(0);
        return alipayConponent.prepay(ordOrder.getOrderNo(),
                ordOrder.getAmount().toString(), goodsInfo.getGoodsName());
    }
}
