package org.monkey.wxpay.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.monkey.wxpay.component.WxPayer;
import org.monkey.wxpay.model.addorder.AddOrderExtensionResp;
import org.monkey.wxpay.model.addorder.AddOrderReq;
import org.monkey.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WxPayServiceImpl
 *
 * @author cc
 * @since 2024/5/24 18:04
 */
@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {
    @Autowired
    private WxPayer wxPayer;

    public void addOrder() {
        AddOrderReq addOrderReq = new AddOrderReq();
        AddOrderExtensionResp addOrderExtensionResp = wxPayer.addOrder(addOrderReq);
        System.out.println(addOrderExtensionResp);
    }
}
