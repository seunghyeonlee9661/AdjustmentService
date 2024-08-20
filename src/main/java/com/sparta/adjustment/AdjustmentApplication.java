package com.sparta.adjustment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

@SpringBootApplication
public class AdjustmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdjustmentApplication.class, args);
	}
}