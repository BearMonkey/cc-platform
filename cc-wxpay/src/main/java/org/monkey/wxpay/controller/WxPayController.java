package org.monkey.wxpay.controller;

import org.monkey.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/pay")
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    @PostMapping("/order")
    public String addOrder() {
        wxPayService.addOrder();
        return "success";
    }
}
