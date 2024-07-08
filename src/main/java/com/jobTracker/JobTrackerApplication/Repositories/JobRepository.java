package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.JobDetails.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findAllByJobUrlAndSavedFromExtensionTrue(String jobUrl);
    Integer countByJobUrlAndSavedFromExtensionTrue(String jobUrl);
    Job findByJobId(String jobId);
}
