package org.monkey.platform.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.auth.dto.LoginDto;
import org.monkey.platform.auth.dto.LoginResp;
import org.monkey.platform.auth.exception.LoginException;
import org.monkey.platform.auth.pojo.Account;
import org.monkey.platform.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.monkey.platform.api.common.Constants;
import org.monkey.platform.api.common.Result;

/**
 * AccountController
 *
 * @author cc
 * @since 2024/5/31 16:39
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginResp> login(@RequestBody LoginDto loginDto) {
        try {
            LoginResp loginResp = loginService.login(loginDto);
            return Result.success(loginResp);
        } catch (Exception e) {
            if (e instanceof LoginException) {
                return Result.fail(e.getMessage());
            }
            log.info(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }

    @GetMapping("/logout")
    public Result<Account> selectByAccountAndPwd(@RequestBody LoginDto loginDto) {
        try {
            return Result.success();
        } catch (Exception e) {
            log.info(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }
}
