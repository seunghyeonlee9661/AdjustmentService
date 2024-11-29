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
            long recordStartTime = System.currentTimeMillis();
            logger.info("일간 기록 작업을 시작합니다.");  // 테스트 로그 메시지
            adjustService.setDailyRecord();
            long recordEndTime = System.currentTimeMillis();
            logger.info("일간 기록 작업이 완료되었습니다.");  // 테스트 로그 메시지
            logger.info("일간 기록 작업 소요 시간: {} ms", (recordEndTime - recordStartTime));  // 소요 시간 로그 추가

            // 일간 정산 작업 시작 시간 측정
            long summaryStartTime = System.currentTimeMillis();
            logger.info("일간 정산 작업을 시작합니다.");  // 테스트 로그 메시지
            adjustService.setDailySummaryAsync();
            long summaryEndTime = System.currentTimeMillis();
            logger.info("일간 정산 작업이 완료되었습니다.");  // 테스트 로그 메시지
            logger.info("일간 정산 작업 소요 시간: {} ms", (summaryEndTime - summaryStartTime));  // 소요 시간 로그 추가
            // 원하는 비지니스 로직 작성
            return RepeatStatus.FINISHED;
        });
    }
}
