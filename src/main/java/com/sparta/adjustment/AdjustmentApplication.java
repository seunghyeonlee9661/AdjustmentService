package com.sparta.adjustment;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = "com.sparta.adjustment") // 필요한 패키지 경로 지정
public class AdjustmentApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AdjustmentApplication.class, args);
	}

}
