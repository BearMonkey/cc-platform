package org.monkey.platform.auth.service.token;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.auth.enums.TokenStorageEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * AbstractStoreTokenMethodFactory
 *
 * @author cc
 * @since 2024/6/7 14:02
 */
@Slf4j
@Component
public class StoreTokenMethodFactory implements InitializingBean, ApplicationContextAware {

    /** spring 容器 */
    private ApplicationContext appContext;

    private static final Map<TokenStorageEnum, IStoreTokenMethod> tokenStorageMap = new HashMap<>();
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        appContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        // 将 Spring 容器中所有的 Payment 接口实现类注册到 payStrategies
        appContext.getBeansOfType(IStoreTokenMethod.class)
                .values()
                .forEach(tokenStorage -> tokenStorageMap.put(tokenStorage.getTokenStorage(), tokenStorage));
    }

    /**
     * 获取策略
     * @param storeToken DB or Redis
     * @return IStoreTokenMethod实现
     */
    public static IStoreTokenMethod getStoreTokenMethod(String storeToken) {
        TokenStorageEnum byValue = TokenStorageEnum.getByValue(storeToken);
        return tokenStorageMap.get(byValue);
    }
}
