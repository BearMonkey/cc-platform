package org.monkey.platform.auth.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.monkey.platform.api.common.Result;
import org.monkey.platform.auth.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * @author cc
 * @since 2024/6/25 16:52
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/check/{token}")
    public Result<Object> checkToken(@PathVariable("token") String token) {
        try {
            boolean checked = JwtUtil.checkToken(token);
            if (checked) {
                return Result.success();
            }
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                return Result.fail("token过期");
            }
        }
        return Result.fail("token校验失败");
    }
}
