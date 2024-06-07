package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.Jobs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends MongoRepository<Jobs,String> {

    public List findAllByUserId(String userId);
}
