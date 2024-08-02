package com.example.springbootquartzjpa.controller;

import com.example.springbootquartzjpa.config.DataNotFoundException;
import com.example.springbootquartzjpa.entity.Tasks;
import com.example.springbootquartzjpa.job.TaskJob;
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

    @Autowired
    private QuartzSchedulerService quartzSchedulerService;

    @PostMapping("")
    public ResponseEntity<String> schedulerTask(@RequestBody Tasks tasks) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("tasks" , tasks);

            String jobId = UUID.randomUUID().toString();

            JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                    .usingJobData(jobDataMap)
                    .withIdentity(jobId, "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobId + " - TaskJobTrigger", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * 1/1 * ? *"))
                    .build();

            scheduler.scheduleJob(jobDetail,trigger);
            return ResponseEntity.ok("Task scheduled successfully!");
        }catch (DataNotFoundException e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error scheduling task: " + e.getMessage());
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<Tasks>> getAll(){
        List<Tasks> tasks = quartzSchedulerService.getAll();
        return ResponseEntity.ok(tasks);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            quartzSchedulerService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            quartzSchedulerService.deleteAll();
            return ResponseEntity.ok("Task deleted successfully");
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
