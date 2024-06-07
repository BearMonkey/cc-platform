package org.monkey.platform.auth.service.token;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class StoreTokenToDb extends ServiceImpl<CcLoginMapper, CcLogin> implements IStoreTokenMethod {
    @Override
    public TokenStorageEnum getTokenStorage() {
        return TokenStorageEnum.DB;
    }

    @Override
    public void storeToken(CcLogin ccLogin) {
        log.info("StoreTokenToDb:{}", ccLogin);
        int cnt = baseMapper.insert(ccLogin);
        if (cnt != 1) {
            log.error("StoreTokenToDb:insert failed");
        }
    }

    @Override
    public void removeToken(CcLogin ccLogin) {
    }

    /**
     *
     * @param ccLogin 用户信息
     * @param source 来源
     * @return 已登录 true 未登录 false
     */
    /**
     * 检查是否登录
     * @param loginDto loginDto
     * @return 已登录,登录信息对象，未登录，null
     */
    @Override
    public CcLogin checkLogin(LoginDto loginDto) {
        log.info("checkLogin:{}", loginDto);
        LambdaQueryWrapper<CcLogin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CcLogin::getUsername, loginDto.getUsername());
        queryWrapper.eq(CcLogin::getSource, loginDto.getSource());
        return baseMapper.selectOne(queryWrapper);
    }
}
