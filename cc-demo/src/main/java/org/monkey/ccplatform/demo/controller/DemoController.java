package org.monkey.ccplatform.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ort.monkey.ccplatform.api.common.Result;
import ort.monkey.ccplatform.api.dto.CcOrderDto;

@RestController
@RequestMapping("/demo")
@Api(value = "demo接口", tags = "/demo")
public class DemoController {

    @ApiOperation(value = "/say", httpMethod = "GET")
    @ApiParam("无")
    @GetMapping("/say")
    public String hello() {
        return "Hello World!";
    }

    @ApiOperation(value = "/add", httpMethod = "POST")
    @ApiParam(value = "cc")
    @PostMapping("/add")
    public Result<CcOrderDto> addOrder(CcOrderDto ccOrderDto) {
        return new Result<>("200", "hahah", ccOrderDto);
    }
}
