package org.monkey.demo.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author cc
 * @since 2024/6/26 15:23
 */
@RestController("/security")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public Result<String> test() {
        log.info("Receive request, /security/test");
        return Result.success();
    }
}
