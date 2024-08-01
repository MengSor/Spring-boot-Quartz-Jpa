package com.example.springbootquartzjpa.controller;

import com.example.springbootquartzjpa.entity.Tasks;
import com.example.springbootquartzjpa.job.TaskJob;
import com.example.springbootquartzjpa.repository.TaskRepository;
import com.example.springbootquartzjpa.service.QuartzSchedulerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

//    private final TaskRepository taskRepository;
//
//    public TaskController(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//    @GetMapping
//    public List<Tasks>  getAll(){
//        return taskRepository.findAll();
//    }
//    @PostMapping
//    public Tasks save(@RequestBody Tasks tasks) {
//        return taskRepository.save(tasks);
//    }
    @Autowired
    private Scheduler scheduler;

    @PostMapping("/schedule")
    public String schedulerTask(@RequestBody Tasks tasks) throws SchedulerException {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("tasks" , tasks);

            String jobId = UUID.randomUUID().toString();

            JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                    .usingJobData(jobDataMap)
                    .withIdentity(jobId, "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobId + "TaskJobTrigger", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * 1/1 * ? *"))
                    .build();

            scheduler.scheduleJob(jobDetail,trigger);
            return "Task scheduled successfully!";
        }catch (ConfigDataNotFoundException e){
                e.printStackTrace();
                return "Error scheduling task: " + e.getMessage();
        }
    }
//    @Autowired
//    private QuartzSchedulerService quartzSchedulerService;
//    @PostMapping("/schedule")
//    public ResponseEntity<String> scheduleTask(@RequestBody Tasks tasks) {
//        try {
//            quartzSchedulerService.save(tasks);
//            return ResponseEntity.ok("Task scheduled successfully");
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scheduling task: " + e.getMessage());
//        }
//
//
//    }
}
