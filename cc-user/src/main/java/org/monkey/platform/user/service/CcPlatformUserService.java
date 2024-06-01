package org.monkey.platform.user.service;

import org.monkey.platform.api.dto.user.CcPlatformUserDto;
import org.monkey.platform.api.exception.CommException;

/**
 * CcPlatformUserService
 *
 * @author cc
 * @since 2024/5/31 9:43
 */
public interface CcPlatformUserService {
    /**
     * 新增用户
     *
     * @param ccPlatformUserDto ccPlatformUserDto
     * @throws CommException 业务异常
     */
    void addUser(CcPlatformUserDto ccPlatformUserDto) throws CommException;

    /**
     * 修改用户信息
     * @param user 用户信息
     * @throws CommException 业务异常
     */
    void modifyUser(CcPlatformUserDto user) throws CommException;
}
