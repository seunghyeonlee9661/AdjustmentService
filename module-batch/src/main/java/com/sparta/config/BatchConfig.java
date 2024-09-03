package com.sparta.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {

    @Bean
    public Job testJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) throws DuplicateJobException {
        Job job = new JobBuilder("testJob",jobRepository)
                .start(testStep(jobRepository,transactionManager))
                .build();
        return job;
    }

    public Step testStep(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        Step step = new StepBuilder("testStep",jobRepository)
                .tasklet(testTasklet(),transactionManager)
                .build();
        return step;
    }

    public Tasklet testTasklet(){
        return ((contribution, chunkContext) -> {
            System.out.println("***** hello batch! *****");
            // 원하는 비지니스 로직 작성
            return RepeatStatus.FINISHED;
        });
    }


//    @Bean
//    public Tasklet dailyTasklet() {
//        return (contribution, chunkContext) -> {
//            logger.info("Starting daily record process...");  // 테스트 로그 메시지
//            adjustService.setDailyRecord();
//            logger.info("Completed daily record process.");  // 테스트 로그 메시지
//
//            logger.info("Starting daily top process...");  // 테스트 로그 메시지
//            adjustService.setDailyTop();
//            logger.info("Completed daily top process.");  // 테스트 로그 메시지
//
//            logger.info("Starting weekly top process...");  // 테스트 로그 메시지
//            adjustService.setWeeklyTop();
//            logger.info("Completed weekly top process.");  // 테스트 로그 메시지
//
//            logger.info("Starting monthly top process...");  // 테스트 로그 메시지
//            adjustService.setMonthlyTop();
//            logger.info("Completed monthly top process.");  // 테스트 로그 메시지
//            return RepeatStatus.FINISHED;
//        };
//    }

}
