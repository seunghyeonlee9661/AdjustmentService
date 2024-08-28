package com.sparta.video;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.common.entity")  // 엔티티 패키지 경로 지정
@ComponentScan(basePackages = {"com.sparta.video", "com.common"})  // 두 모듈의 컴포넌트 스캔
@EnableJpaRepositories(basePackages = "com.common.repository")  // 리포지토리 스캔
@EnableJpaAuditing
public class VideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }

}
