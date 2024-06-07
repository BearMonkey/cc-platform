package org.monkey.platform.auth.service.token;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.auth.dto.LoginDto;
import org.monkey.platform.auth.enums.TokenStorageEnum;
import org.monkey.platform.auth.mapper.CcLoginMapper;
import org.monkey.platform.auth.pojo.CcLogin;
import org.springframework.stereotype.Service;

/**
 * StoreTokenToDb
 *
 * @author cc
 * @since 2024/6/7 11:39
 */
@Slf4j
@Service
public class StoreTokenToRedis extends ServiceImpl<CcLoginMapper, CcLogin> implements IStoreTokenMethod  {

    @Override
    public TokenStorageEnum getTokenStorage() {
        return TokenStorageEnum.REDIS;
    }

    @Override
    public void storeToken(CcLogin ccLogin) {

    }

    @Override
    public void removeToken(CcLogin ccLogin) {

    }

    @Override
    public CcLogin checkLogin(LoginDto loginDto) {
        return null;
    }
}
