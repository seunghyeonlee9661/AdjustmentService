package com.sparta.user;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@EnableJpaAuditing
@EntityScan(basePackages = "com.common.entity")  // 엔티티 패키지 경로 지정
@ComponentScan(basePackages = {"com.sparta.user", "com.common"})  // 두 모듈의 컴포넌트 스캔
@EnableJpaRepositories(basePackages = "com.common.repository")  // 리포지토리 스캔
@SpringBootApplication
public class UserApplication {

    // 현재 시간 설정
    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
