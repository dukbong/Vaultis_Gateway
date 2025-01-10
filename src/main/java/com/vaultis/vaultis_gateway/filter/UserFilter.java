package com.vaultis.vaultis_gateway.filter;

import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Component
public class UserFilter extends AbstractGatewayFilterFactory<UserFilter.Config> {
    public UserFilter() {
        super(Config.class);
    }

    public static class Config{}

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
           ServerHttpRequest request = exchange.getRequest();
           ServerHttpResponse response = exchange.getResponse();

           HttpCookie googleCookie = request.getCookies().getFirst("google_webgram");
           String googleCookieValue = googleCookie != null ? googleCookie.getValue() : null;
           if(StringUtils.hasText(googleCookieValue)) {
               ServerHttpRequest modRequest = request.mutate().header(HttpHeaders.AUTHORIZATION, "Bearer " + googleCookieValue).build();

               return chain.filter(exchange.mutate().request(modRequest).build())
                       .then(Mono.fromRunnable(() -> {
                            // 후속
                       }));
           }
           return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 후속
           }));
        });
    }
}
