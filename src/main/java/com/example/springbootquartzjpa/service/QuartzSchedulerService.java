package com.example.springbootquartzjpa.service;

import com.example.springbootquartzjpa.entity.Tasks;
import com.example.springbootquartzjpa.job.TaskJob;


import com.example.springbootquartzjpa.repository.TaskRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzSchedulerService {
    @Autowired
   private TaskRepository taskRepository;

    public Tasks save(Tasks taskJob){
       return taskRepository.save(taskJob);
    }

//    @Autowired
//    private Scheduler scheduler;
//
//    public void save(Tasks tasks) throws SchedulerException {
//        JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
//                .withIdentity("sampleJob", "group1")
//                 .usingJobData("tasks", tasks)
//                .storeDurably()
//                .build();
//
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("sampleTrigger", "group1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 */2 * 1/1 * ? *"))
//                .forJob(jobDetail)
//                .build();
//
//        scheduler.scheduleJob(jobDetail, trigger);
//    }
}
