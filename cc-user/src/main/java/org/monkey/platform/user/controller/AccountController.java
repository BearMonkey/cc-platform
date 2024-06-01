package org.monkey.platform.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ort.monkey.ccplatform.api.common.Constants;
import ort.monkey.ccplatform.api.common.Result;
import ort.monkey.ccplatform.api.dto.user.AccountDto;

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
    public Result<String> addAccount(AccountDto accountDto) {
        try {
            accountService.addAccount(accountDto);
            return Result.success();
        } catch (Exception e) {
            log.info(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }
}
