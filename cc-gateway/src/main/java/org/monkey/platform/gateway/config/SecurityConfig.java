package org.monkey.platform.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * SecurityConfig
 *
 * @author cc
 * @since 2024/6/25 15:23
 */
public class SecurityConfig extends AbstractGatewayFilterFactory{
    @Override
    public GatewayFilter apply(String routeId, Consumer consumer) {
        return super.apply(routeId, consumer);
    }

    @Override
    public GatewayFilter apply(Consumer consumer) {
        return super.apply(consumer);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            // 自定义过滤逻辑
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 请求结束后的逻辑
            }));
        };
    }

    @Override
    public GatewayFilter apply(String routeId, Object config) {
        return super.apply(routeId, config);
    }

    @Override
    public String name() {
        return super.name();
    }
}
