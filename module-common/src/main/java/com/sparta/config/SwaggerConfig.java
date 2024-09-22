package com.sparta.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
public class SwaggerConfig {

    @Value("${openapi.info.title}")
    private String title;
    @Value("${openapi.info.description}")
    private String description;
    @Value("${openapi.info.version}")
    private String version;
    @Value("${openapi.url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(Collections.singletonList(new Server().url(url))) // 변경된 요청 URL
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(title)
                .description(description)
                .version(version);
    }
}