package com.sparta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);  // 최소 4개의 스레드
        executor.setMaxPoolSize(10);  // 최대 10개의 스레드
        executor.setQueueCapacity(100);  // 큐의 크기
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}