package org.monkey.demo.alipay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.monkey.demo.alipay.dto.OrdOrder;
import org.monkey.demo.alipay.dto.PrepayResp;
import org.monkey.demo.alipay.service.AlipayService;
import org.monkey.platform.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AlipayController
 *
 * @author Monkey
 * @since 2024/6/30
 */
//@RestController
@Controller
@RequestMapping("/api")
@ApiOperation("支付宝支付")
@Api
@Slf4j
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @GetMapping("/prepay")
    public void prepay(String orderId, HttpServletResponse response) {
        log.info("收到预支付订单: {}", orderId);
        PrepayResp prepayResp = alipayService.prepay(orderId);
        log.info("预支付订单处理完成, 结果: {}", prepayResp);
        PrintWriter writer = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            writer = response.getWriter();
            writer.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<meta charset=\"utf-8\">\n" +
                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "\t<title>Test</title>\n" +
                    "</head>\n" +
                    "<body>");
            writer.write(prepayResp.getData());
            writer.write("</body>\n" +
                    "</html>");
        } catch (IOException e) {
            log.info("构建响应失败: ", e);
        }
        //return Result.success(prepayResp);
    }

    @RequestMapping("/notify")
    public String alipayNotify(HttpServletResponse response) {
        return "";
    }
}
