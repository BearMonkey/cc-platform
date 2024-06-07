package org.monkey.platform.auth.service.impl;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.monkey.platform.api.common.Constants;
import org.monkey.platform.api.common.Result;
import org.monkey.platform.api.exception.CommException;
import org.monkey.platform.auth.api.dto.AccountDto;
import org.monkey.platform.auth.config.SysConfig;
import org.monkey.platform.auth.dto.LoginDto;
import org.monkey.platform.auth.dto.LoginResp;
import org.monkey.platform.auth.exception.LoginException;
import org.monkey.platform.auth.feign.AccountFeignClient;
import org.monkey.platform.auth.pojo.CcLogin;
import org.monkey.platform.auth.service.LoginService;
import org.monkey.platform.auth.service.token.IStoreTokenMethod;
import org.monkey.platform.auth.service.token.StoreTokenMethodFactory;
import org.monkey.platform.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginServiceImpl
 *
 * @author cc
 * @since 2024/6/1 18:02
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private AccountFeignClient accountFeignClient;

    @Autowired
    private SysConfig sysConfig;

    @Override
    public LoginResp login(LoginDto loginDto) throws LoginException, CommException {
        // 参数校验
        loginParamCheck(loginDto);

        LoginResp loginResp = new LoginResp();

        IStoreTokenMethod storeTokenMethod = StoreTokenMethodFactory.getStoreTokenMethod(sysConfig.getStoreToken());
        // 判断是否已登录
        CcLogin loginStatusCheckRst = storeTokenMethod.checkLogin(loginDto);
        if (null != loginStatusCheckRst) {
            throw new LoginException("不能重复登录");
        }

        // login 流程
        AccountDto accountDto = new AccountDto();
        accountDto.setAccount(loginDto.getUsername());
        accountDto.setPassword(loginDto.getPassword());
        log.info("调用[cc-user]服务查询账户, account={}", accountDto.getAccount());
        Result<AccountDto> result = accountFeignClient.selectByAccountAndPwd(accountDto);
        if (null == result) {
            throw new CommException("调用[cc-user]异常，返回值为空");
        }
        log.info("调用[cc-user]服务查询账户, result={}", result);
        if (StringUtils.isEmpty(result.getCode()) || !result.getCode().equals(Constants.SUCCESS)) {
            throw new CommException("调用[cc-user]失败, msg=" + result.getMsg());
        }
        if (null == result.getData()) {
            throw new LoginException("用户名密码错误");
        }

        try {
            AccountDto accountResp = JSON.parseObject(JSON.toJSONString(result.getData()), AccountDto.class);
            // 查询账户存在，生成token
            Map<String, Object> map = new HashMap<>();
            map.put("id", accountResp.getId());
            map.put("username", accountResp.getAccount());
            map.put("nickName", accountResp.getNickName());
            String token = JwtUtil.createToken(map);

            // 添加登录记录 token 存储
            log.info("token 存储:");
            CcLogin ccLogin = new CcLogin();
            ccLogin.setToken(token);
            ccLogin.setUsername(accountResp.getAccount());
            ccLogin.setSource(loginDto.getSource());
            storeTokenMethod.storeToken(ccLogin);

            // 构造返回结果
            loginResp.setToken(token);
            loginResp.setUsername(accountDto.getAccount());
            return loginResp;
        } catch (Exception e) {
            throw new CommException("系统服务异常", e);
        }
    }

    // 检查登录状态
    private void loginStatusCheck(IStoreTokenMethod storeTokenMethod, LoginDto loginDto) throws LoginException {
    }

    /**
     * 登录参数验证
     * @param loginDto loginDto
     * @throws LoginException LoginException
     */
    private void loginParamCheck(LoginDto loginDto) throws LoginException, CommException {
        try {
            Assert.isTrue(null != loginDto, "账号密码不能为空");
            Assert.isTrue(StringUtils.isNotEmpty(loginDto.getUsername()), "账号密码不能为空");
            Assert.isTrue(StringUtils.isNotEmpty(loginDto.getPassword()), "账号密码不能为空");
            Assert.isTrue(StringUtils.isNotEmpty(loginDto.getCode()), "验证码不能为空");
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
        if (StringUtils.isEmpty(loginDto.getCode())) {
            throw new CommException("没有source");
        }
    }
}
