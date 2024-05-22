package org.monkey.ccplatform.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ort.monkey.ccplatform.api.common.Result;
import ort.monkey.ccplatform.api.dto.CcOrderDto;

@RestController
@RequestMapping("/demo")
@Api(value = "demo接口", tags = "/demo")
public class DemoController {

    @Value("${org.monkey.name:Ashe}")
    private String name;

    @ApiOperation(value = "/say", httpMethod = "GET")
    @ApiParam(name = "xxx", value = "测试用的", required = true)
    @GetMapping("/say")
    public String hello(String xxx) {
        return "Hello World, " + name;
    }

    @ApiOperation(value = "/add", httpMethod = "POST")
    @ApiParam(value = "cc")
    @PostMapping("/add")
    public Result<CcOrderDto> addOrder(@RequestBody CcOrderDto ccOrderDto) {
        return new Result<>("200", "hahah", ccOrderDto);
    }
}
