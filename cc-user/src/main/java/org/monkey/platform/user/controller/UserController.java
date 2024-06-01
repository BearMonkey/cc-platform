package org.monkey.platform.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.user.service.CcPlatformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.monkey.platform.api.common.Constants;
import org.monkey.platform.api.common.Result;
import org.monkey.platform.api.dto.user.CcPlatformUserDto;
import org.monkey.platform.api.exception.CommException;

/**
 * UserController
 *
 * @author cc
 * @since 2024/5/31 10:07
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Autowired
    private CcPlatformUserService ccPlatformUserService;

    @PostMapping("/add")
    public Result<String> addUser(@RequestBody CcPlatformUserDto user) {
        try {
            /*String yyyyMMdd = DateFormatUtils.format(new Date(), "yyyyMMdd");
            Date date = DateUtils.parseDate("yyyyMMdd", yyyyMMdd);
            log.info("日期工具类测试：{}", yyyyMMdd);
            log.info("日期工具类测试：{}", date);*/
            ccPlatformUserService.addUser(user);
            return Result.success();
        } catch (CommException e) {
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }

    @PutMapping("/modify")
    public Result<String> modifyUser(@RequestBody CcPlatformUserDto user) {
        try {
            ccPlatformUserService.modifyUser(user);
            return Result.success();
        } catch (CommException e) {
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error(Constants.FAIL_UNKNOWN, e);
            return Result.fail(Constants.FAIL_UNKNOWN);
        }
    }
}
