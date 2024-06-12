package org.monkey.platform.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.Base64Decoder;
import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.api.exception.CommException;
import org.monkey.platform.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.monkey.platform.api.common.Constants;
import org.monkey.platform.api.common.Result;
import org.monkey.platform.auth.api.dto.AccountDto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AccountController
 *
 * @author cc
 * @since 2024/5/31 16:39
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public Result<String> addAccount(@RequestBody AccountDto accountDto) {
        try {
            accountService.addAccount(accountDto);
            return Result.success();
        } catch (Exception e) {
            log.info(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }

    @GetMapping("/user/{accountInfo}")
    public Result<AccountDto> selectByAccountAndPwd(@PathVariable("accountInfo") String accountInfo) {
        if (accountInfo == null || accountInfo.isEmpty()) {
            return Result.fail("账户信息不能为空");
        }
        byte[] decode = Base64.getDecoder().decode(accountInfo);
        String accountStr = new String(decode, StandardCharsets.UTF_8);
        try {
            AccountDto accountDto = JSONObject.parseObject(accountStr, AccountDto.class);
            log.info("accountDto:{}", accountDto);
            AccountDto accountRst = accountService.selectAccount(accountDto.getAccount(), accountDto.getPassword());
            return Result.success(accountRst);
        } catch (CommException e) {
            log.info(e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.info(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }
}
