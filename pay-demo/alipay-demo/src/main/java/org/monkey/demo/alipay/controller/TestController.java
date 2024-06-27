package org.monkey.demo.alipay.controller;

import org.monkey.platform.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author cc
 * @since 2024/6/27 16:21
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test/{msg}")
    public Result<String> test(@PathVariable("msg") String msg) {
        return Result.success(msg);
    }
}
