package com.sparta;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackages = {"com.common.entity", "com.sparta.entity"})
@ComponentScan(basePackages = {"com.sparta", "com.common"})
@EnableJpaRepositories(basePackages = {"com.common.repository", "com.sparta.repository"})
@EnableJpaAuditing
@EnableDiscoveryClient
public class AdjustApplication {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(AdjustApplication.class, args);
    }
}
