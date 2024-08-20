package com.sparta.adjustment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AdjustmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdjustmentApplication.class, args);
	}
}