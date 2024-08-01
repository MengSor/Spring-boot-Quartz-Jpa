package com.example.springbootquartzjpa.config;

import com.example.springbootquartzjpa.job.TaskJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class QuartzConfig {

//    @Bean
//    public JobDetail sampleJobDetail() {
//        return JobBuilder.newJob(TaskJob.class)
//                .withIdentity("sampleJob")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public Trigger sampleJobTrigger() {
//        return TriggerBuilder.newTrigger()
//                .forJob(sampleJobDetail())
//                .withIdentity("sampleTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 */3 * 1/1 * ? *")) // Runs every minute
//                .build();
//    }

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        return schedulerFactoryBean;
//    }
}
