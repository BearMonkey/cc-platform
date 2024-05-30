package org.monkey.demo.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author cc
 * @since 2024/5/30 16:49
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @GetMapping("/test")
    public String test() {
        log.info("======================test======================");
        return "test";
    }
}
