package org.monkey.platform.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.monkey.platform.common.Result;
import org.monkey.platform.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * LoginFilter
 *
 * @author cc
 * @since 2024/6/24 14:03
 */

@Component
@Slf4j
public class CcGlobalFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String method = request.getMethodValue();
        String host = request.getURI().getHost();
        int port = request.getURI().getPort();
        String path = request.getURI().getPath();
        log.info("Request:{}, {}:{} {}", method, host, port, path);
        if (matcher.match("/login/**", path)) {
            return chain.filter(exchange);
        } else {
            HttpHeaders headers = request.getHeaders();
            Result<Object> authResult = authService.checkAuth(headers.get(HttpHeaders.AUTHORIZATION));
            if (null == authResult || StringUtils.isEmpty(authResult.getCode())) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            if (!"1".equals(authResult.getCode())) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
