package com.sparta.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionNotificationListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // Job 시작 전에 처리할 로직
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // Job 완료 후 처리할 로직
        System.out.println("Batch job completed with status: " + jobExecution.getStatus());
    }
}
