package com.example.springbootquartzjpa.job;

import com.example.springbootquartzjpa.entity.Tasks;
import com.example.springbootquartzjpa.repository.TaskRepository;

import com.example.springbootquartzjpa.service.QuartzSchedulerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;


@Component
public class TaskJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(TaskJob.class);
    @Autowired
   private QuartzSchedulerService quartzSchedulerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("Job started at:" + new Date());

        try {
            Tasks tasks = (Tasks)  jobExecutionContext.getMergedJobDataMap().get("tasks");

            if (tasks != null){
                quartzSchedulerService.save(tasks);
                log.info("Job executed successfully");
            }else {
                log.warn("No tasks data found in JobExecutionContext.");
            }
        }catch (Exception e){
            log.error("Error executing job: ", e);
            throw new JobExecutionException(e);
        }finally {
            log.info("Job ended at: " + new Date());
        }




//        log.info("Start: " + new Date());
//        try {
//            Tasks tasks = new Tasks();
//            tasks.setName("FA");
//            tasks.setDescription("Hello world");
//
//            quartzSchedulerService.save(tasks);
//            log.info("Job executed successfully");
//        }catch (Exception e){
//            log.error("Error executing JOb" , e);
//            throw new JobExecutionException(e);
//        }finally {
//            log.info("Job ended at: " + new Date());
//        }
//       Tasks tasks = new Tasks();
//       tasks.getName();
//       tasks.getDescription();
//       quartzSchedulerService.save(tasks);
    }
}
