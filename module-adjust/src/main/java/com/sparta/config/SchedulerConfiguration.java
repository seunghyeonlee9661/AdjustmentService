package com.sparta.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dailyJob;

    @Scheduled(cron = "0 0 12 * * ?")
    public void runDailyJob() throws Exception {
        jobLauncher.run(dailyJob, new JobParametersBuilder().toJobParameters());
    }
}
