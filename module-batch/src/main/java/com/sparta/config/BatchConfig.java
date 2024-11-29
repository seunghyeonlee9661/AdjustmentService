package com.sparta.config;
import com.sparta.service.AdjustService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
    private final AdjustService adjustService;

    @Bean
    public Job dailyJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new JobBuilder("dailyJob",jobRepository)
                .start(dailyStep(jobRepository,transactionManager))
                .build();
    }

    public Step dailyStep(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        return new StepBuilder("dailyStep",jobRepository)
                .tasklet(dailyTasklet(),transactionManager)
                .build();
    }

    public Tasklet dailyTasklet(){
        return ((contribution, chunkContext) -> {
            adjustService.setDailyRecord();
            logger.info("Completed daily record process.");  // 테스트 로그 메시지
            adjustService.setDailyTop();
            logger.info("Completed daily top process.");  // 테스트 로그 메시지
            adjustService.setWeeklyTop();
            logger.info("Completed weekly top process.");  // 테스트 로그 메시지
            adjustService.setMonthlyTop();
            logger.info("Completed monthly top process.");  // 테스트 로그 메시지
            // 원하는 비지니스 로직 작성
            return RepeatStatus.FINISHED;
        });
    }
}
