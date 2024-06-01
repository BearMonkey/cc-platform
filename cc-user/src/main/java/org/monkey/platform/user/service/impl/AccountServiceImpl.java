package org.monkey.platform.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.monkey.platform.user.config.SysConfig;
import org.monkey.platform.user.mapper.AccountMapper;
import org.monkey.platform.user.pojo.Account;
import org.monkey.platform.user.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.monkey.platform.api.dto.user.AccountDto;
import org.monkey.platform.api.enums.user.DelFlagEnum;
import org.monkey.platform.api.exception.CommException;
import org.monkey.platform.crypto.EnctyptUtil;

import java.util.Date;

/**
 * AccountServiceImpl
 *
 * @author cc
 * @since 2024/5/31 16:26
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SysConfig sysConfig;

    @Override
    public void addAccount(AccountDto accountDto) throws CommException {
        // 参数校验
        paramCheck(accountDto);

        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        account.setPassword(EnctyptUtil.hmacsha256(account.getPassword(), sysConfig.getHmacsha256key()));
        account.setCreateTime(new Date());
        account.setCreateUser("System");
        account.setDelFlag(DelFlagEnum.USEING.getCode());
        try {
            int row = accountMapper.insert(account);
            if (row != 1) {
                throw new CommException("数据插入失败, row!=1");
            }
        } catch (Exception e) {
            log.error("数据库执行异常: ", e);
            throw new CommException("数据库执行异常");
        }
    }

    @Override
    public Account selectAccount(String username, String password) throws CommException {
        if (StringUtils.isEmpty(username)) {
            throw new CommException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new CommException("密码不能为空");
        }
        try {
            LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Account::getAccount, username);
            queryWrapper.eq(Account::getPassword, EnctyptUtil.hmacsha256(password, sysConfig.getHmacsha256key()));
            Account account = accountMapper.selectOne(queryWrapper);
            if (null == account) {
                throw new CommException("账号或密码错误");
            }
            return account;
        } catch (Exception e) {
            log.error("数据库执行异常: ", e);
            throw new CommException("数据库执行异常");
        }
    }

    /**
     * 参数校验
     * @param accountDto accountDto
     */
    private void paramCheck(AccountDto accountDto) throws CommException {
        try {
            Assert.isTrue(StringUtils.isNotEmpty(accountDto.getAccount()), "账号不能为空");
            Assert.isTrue(StringUtils.isNotEmpty(accountDto.getTel()), "手机号不能为空");
            Assert.isTrue(StringUtils.isNotEmpty(accountDto.getPassword()), "手机号不能为空");
        } catch (Exception e) {
            throw new CommException(e.getMessage());
        }
    }
}
