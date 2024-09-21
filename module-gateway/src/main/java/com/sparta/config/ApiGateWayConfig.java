package com.sparta.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder) {
        return routeBuilder.routes()
                .route("securities-service-openapi", r -> {
                    return r.path("/api/securities-service/v3/api-docs/**")
                            .filters(f -> f.rewritePath(
                                    "/api/securities-service/(?<path>.*)", "/${path}"))
                            .uri("lb://SECURITIES-SERVICE");
                })
                .route("securities-service", r -> {
                    return r.path("/stockprice/**", "/stockinfo/**")
                            .uri("lb://SECURITIES-SERVICE");
                })
                .build();
    }
}