package com.sparta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.common.entity", "com.sparta.entity"})
@ComponentScan(basePackages = {"com.sparta", "com.common"})
@EnableJpaRepositories(basePackages = {"com.common.repository", "com.sparta.repository"})
@EnableJpaAuditing
public class AdjustApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdjustApplication.class, args);
    }

}
