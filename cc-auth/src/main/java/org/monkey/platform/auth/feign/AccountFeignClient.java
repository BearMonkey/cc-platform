package org.monkey.platform.auth.feign;

import org.monkey.platform.api.common.Result;
import org.monkey.platform.auth.api.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * AccountFeignClient
 *
 * @author cc
 * @since 2024/6/7 10:05
 */
@FeignClient(value = "cc-user")
public interface AccountFeignClient {

    @GetMapping("/account/user/{accountInfo}")
    Result<AccountDto> selectByAccountAndPwd(@PathVariable("accountInfo") String accountInfo);
}
