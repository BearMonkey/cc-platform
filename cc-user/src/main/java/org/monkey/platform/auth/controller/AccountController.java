package org.monkey.platform.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.monkey.platform.api.common.Constants;
import org.monkey.platform.api.common.Result;
import org.monkey.platform.auth.api.dto.AccountDto;

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

    @GetMapping("/user")
    public Result<AccountDto> selectByAccountAndPwd(AccountDto accountDto) {
        try {
            AccountDto accountRst = accountService.selectAccount(accountDto.getAccount(), accountDto.getPassword());
            return Result.success(accountRst);
        } catch (Exception e) {
            log.info(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }
}
