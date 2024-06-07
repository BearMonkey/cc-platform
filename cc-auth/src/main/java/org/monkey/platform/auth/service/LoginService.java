package org.monkey.platform.auth.service;

import org.monkey.platform.api.exception.CommException;
import org.monkey.platform.auth.dto.LoginDto;
import org.monkey.platform.auth.dto.LoginResp;
import org.monkey.platform.auth.exception.LoginException;

/**
 * LoginService
 *
 * @author cc
 * @since 2024/6/1 18:01
 */
public interface LoginService {
    LoginResp login(LoginDto loginDto) throws LoginException, CommException;
}
