package org.monkey.platform.user.service;

import org.monkey.platform.user.pojo.Account;
import org.monkey.platform.api.dto.user.AccountDto;
import org.monkey.platform.api.exception.CommException;

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

    /**
     * 根据用户名密码查询账号
     * @param username 账号
     * @param password 密码
     * @return {@link Account}
     * @throws CommException 业务异常
     */
    Account selectAccount(String username, String password) throws CommException;
}
