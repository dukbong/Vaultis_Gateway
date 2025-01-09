package com.vaultis.vaultis_gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public static class Config {
    }

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
                log.info("Global Filter Start : request id -> {}", request.getId());
                log.info("Global Filter start : request uri -> {}", request.getURI());
                log.info("Global Filter start : request header -> {}", request.getHeaders().get("Authorization"));
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                    log.info("Global Filter End : response code -> {}", response.getStatusCode());
            }));
        };
    }
}