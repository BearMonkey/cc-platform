package org.monkey.platform.user.service;

import ort.monkey.ccplatform.api.dto.BaseDto;
import ort.monkey.ccplatform.api.dto.user.AccountDto;
import ort.monkey.ccplatform.api.exception.CommException;

/**
 * AccountService
 *
 * @author cc
 * @since 2024/5/31 16:26
 */
public interface AccountService {
    /**
     * 添加账号
     * @param accountDto accountDto
     * @throws CommException 业务操作异常
     */
    void addAccount(AccountDto accountDto) throws CommException;
}
