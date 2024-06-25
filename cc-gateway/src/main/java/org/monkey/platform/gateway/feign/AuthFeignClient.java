package org.monkey.platform.gateway.feign;

import org.monkey.platform.api.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * AuthFeignClient
 *
 * @author cc
 * @since 2024/6/25 16:42
 */
@FeignClient(value = "cc-auth")
public interface AuthFeignClient {
    @GetMapping("/check/{token}")
    Result<Object> checkToken(@PathVariable("token") String token);
}
