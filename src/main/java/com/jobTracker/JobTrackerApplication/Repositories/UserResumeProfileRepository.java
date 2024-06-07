package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.UserResumeProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserResumeProfileRepository extends MongoRepository<UserResumeProfile,String> {
}
