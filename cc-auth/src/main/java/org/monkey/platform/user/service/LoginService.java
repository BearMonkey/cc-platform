package org.monkey.platform.user.service;

import org.monkey.platform.user.dto.LoginDto;
import org.monkey.platform.user.dto.LoginResp;

/**
 * LoginService
 *
 * @author cc
 * @since 2024/6/1 18:01
 */
public interface LoginService {
    LoginResp login(LoginDto loginDto);
}
