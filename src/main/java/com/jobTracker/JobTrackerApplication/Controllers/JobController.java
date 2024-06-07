package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Configurations.JwtTokenGenerator;
import com.jobTracker.JobTrackerApplication.Entities.Jobs;
import com.jobTracker.JobTrackerApplication.Service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*",allowCredentials = "true")
public class JobController {
    @Autowired
    JobsService jobsService;
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @PostMapping("/addJob")
    private ResponseEntity<String> addJob(@RequestBody  Map<String,Object> jobMap){
        try {
            Jobs job = new Jobs();
            job.setUserId(jwtTokenGenerator.getUserId());
            job.setJobRole((String) jobMap.get("role"));
            job.setJobCompany((String) jobMap.get("company"));
            job.setJobLocation((String) jobMap.get("location"));
            job.setStatus((String) jobMap.get("status"));
            job.setJobUrl((String) jobMap.get("jobUrl"));
            job.setJobSummary((String) jobMap.get("jobSummary"));
            return new ResponseEntity<>(jobsService.addJob(job), HttpStatus.OK);
        }
        catch(Exception e) {
            throw new RuntimeException();
        }

    }
    @GetMapping("/getJobs")
    private ResponseEntity<Map<String,Object>> fetchJobsForUser(){
        Map jobsMap = new HashMap();
        try {
            jobsMap.put("jobs",jobsService.getJobsByUserId(jwtTokenGenerator.getUserId()));
            return new ResponseEntity<>(jobsMap, HttpStatus.OK);
        }
        catch(Exception e) {
            throw new RuntimeException();
        }

    }
}
