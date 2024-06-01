package org.monkey.platform.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.monkey.platform.user.mapper.AccountMapper;
import org.monkey.platform.user.pojo.Account;
import org.monkey.platform.user.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ort.monkey.ccplatform.api.dto.user.AccountDto;
import ort.monkey.ccplatform.api.enums.user.DelFlagEnum;
import ort.monkey.ccplatform.api.exception.CommException;

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

    @Override
    public void addAccount(AccountDto accountDto) throws CommException {
        // 参数校验
        paramCheck(accountDto);

        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
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
