package com.example.springbootquartzjpa.service;

import com.example.springbootquartzjpa.entity.Tasks;
import com.example.springbootquartzjpa.job.TaskJob;


import com.example.springbootquartzjpa.repository.TaskRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class QuartzSchedulerService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Scheduler scheduler;

   public List<Tasks> getAll() {
        return taskRepository.findAll();
   }

    public Tasks save(Tasks taskJob){
        return taskRepository.save(taskJob);
    }

    public void deleteTask(Long id){
       taskRepository.deleteById(id);
    }

    public void deleteAll(){
       taskRepository.deleteAll();
    }

//    public String scheduleTask(Tasks tasks) throws SchedulerException {
//       JobDataMap jobDataMap = new JobDataMap();
//       jobDataMap.put("tasks" , tasks);
//
//       String jobId = UUID.randomUUID().toString();
//
//       JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
//               .usingJobData(jobDataMap)
//               .withIdentity(jobId , "group1")
//               .build();
//
//       Trigger trigger = TriggerBuilder.newTrigger()
//               .withIdentity(jobId + "-trigger" , "group1")
//               .startNow()
//               .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * 1/1 * ? *"))
//               .build();
//
//       scheduler.scheduleJob(jobDetail , trigger);
//       return "Task Scheduled Successfully!!";
//    }

}
