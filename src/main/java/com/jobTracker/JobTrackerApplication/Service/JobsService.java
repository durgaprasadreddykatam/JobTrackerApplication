package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.Jobs;
import com.jobTracker.JobTrackerApplication.Repositories.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JobsService {
    @Autowired
    private JobsRepository jobsRepository;

    public String addJob(Jobs job){
        job.setJobSavedDate(Instant.now());
        Jobs addedJob= jobsRepository.save(job);
        return addedJob.getJobId();

    }
    public List getJobsByUserId(String userId){
        return jobsRepository.findAllByUserId(userId);

    }
}
