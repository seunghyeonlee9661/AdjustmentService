package com.sparta.config;

import com.sparta.service.AdjustService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final AdjustService adjustService;

    @Bean
    public Job dailyJob() {
        return new JobBuilder("dailyJob", jobRepository)
                .start(dailyStep())
                .listener(new JobCompletionNotificationListener())
                .build();
    }

    @Bean
    public Step dailyStep() {
        return new StepBuilder("dailyStep", jobRepository)
                .tasklet(dailyTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyTasklet() {
        return (contribution, chunkContext) -> {
            logger.info("Batch JoB!");
//            logger.info("Starting daily record process...");
//            adjustService.setDailyRecord();
//            logger.info("Completed daily record process.");
//
//            logger.info("Starting daily top process...");
//            adjustService.setDailyTop();
//            logger.info("Completed daily top process.");
//
//            logger.info("Starting weekly top process...");
//            adjustService.setWeeklyTop();
//            logger.info("Completed weekly top process.");
//
//            logger.info("Starting monthly top process...");
//            adjustService.setMonthlyTop();
//            logger.info("Completed monthly top process.");

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public JobCompletionNotificationListener listener() {
        return new JobCompletionNotificationListener();
    }
}
