package com.qiusuo.gatewaysvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QiuSuoGatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(QiuSuoGatewayServer.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("r1", r -> r.path("/communityservice/*")
                        .uri("localhost:8060/")).build();

    }
}
