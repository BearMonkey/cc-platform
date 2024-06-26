package org.monkey.platform.gateway.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.monkey.platform.common.Result;
import org.monkey.platform.gateway.feign.AuthFeignClient;
import org.monkey.platform.gateway.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * AuthServiceImpl
 *
 * @author cc
 * @since 2024/6/25 16:25
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthFeignClient authFeignClient;
    @Override
    public Result<Object> checkAuth(List<String> authList) {
        if (CollectionUtils.isEmpty(authList)) {
            return Result.fail("-1", "无鉴权信息", null);
        }
        String authorization = authList.get(0);
        String[] split = authorization.split("\\s");
        String token = split[1];
        if (StringUtils.isEmpty(token)) {
            return Result.fail("-1", "无鉴权信息", null);
        }
        Result<Object> checkResult = authFeignClient.checkToken(token);
        if (null == checkResult) {
            return Result.fail("-1", "鉴权不通过", null);
        }
        if (!"0".equals(checkResult.getCode())) {
            return Result.fail("-1", checkResult.getMsg(), checkResult.getData());
        }
        return Result.success();
    }
}
