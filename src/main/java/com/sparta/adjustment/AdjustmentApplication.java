package com.sparta.adjustment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = "com.sparta.adjustment")
public class AdjustmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdjustmentApplication.class, args);
	}
}