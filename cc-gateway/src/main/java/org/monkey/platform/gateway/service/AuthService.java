package org.monkey.platform.gateway.service;

import org.monkey.platform.api.common.Result;

import java.util.List;

/**
 * AuthService
 *
 * @author cc
 * @since 2024/6/25 16:24
 */
public interface AuthService {
    /**
     * 认证鉴权
     * @param authList
     * @return
     */
    Result<Object> checkAuth(List<String> authList);
}
