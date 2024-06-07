package org.monkey.platform.auth.service.token;

import org.monkey.platform.auth.dto.LoginDto;
import org.monkey.platform.auth.enums.TokenStorageEnum;
import org.monkey.platform.auth.pojo.CcLogin;

/**
 * StoreTokenMethodFactory
 *
 * @author cc
 * @since 2024/6/7 11:36
 */
public interface IStoreTokenMethod {
    TokenStorageEnum getTokenStorage();
    void storeToken(CcLogin ccLogin);

    void removeToken(CcLogin ccLogin);

    CcLogin checkLogin(LoginDto loginDto);
}
