package org.monkey.platform.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.api.exception.CommException;
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

    @GetMapping("/user/{account}/{pwd}")
    public Result<AccountDto> selectByAccountAndPwd(
            @PathVariable("account") String account,
            @PathVariable("pwd") String pwd) {
        /*if (StringUtils.isEmpty(account)) {
            return Result.fail("账户信息不能为空");
        }
        if (StringUtils.isEmpty(pwd)) {
            return Result.fail("密码不能为空");
        }*/
        try {
            AccountDto accountDto = new AccountDto();
            accountDto.setAccount(account);
            accountDto.setPassword(pwd);
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
