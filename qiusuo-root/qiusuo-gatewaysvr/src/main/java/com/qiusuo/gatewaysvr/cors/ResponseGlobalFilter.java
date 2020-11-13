package com.qiusuo.gatewaysvr.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;


@Component
public class ResponseGlobalFilter implements GlobalFilter, Ordered {
    @Value("${special.graphqlPath}")
    private String graphqlPath;

    @Override
    public int getOrder() {
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        String path = exchange.getRequest().getPath().value();
        if (path.equals(graphqlPath)) {
            String header = originalResponse.getHeaders().getAccessControlAllowOrigin();
            if (originalResponse.getHeaders().getAccessControlAllowOrigin() != null) {
                originalResponse.getHeaders().remove(ACCESS_CONTROL_ALLOW_ORIGIN);
            }
        }
        return chain.filter(exchange.mutate().response(originalResponse).build());
    }
}
