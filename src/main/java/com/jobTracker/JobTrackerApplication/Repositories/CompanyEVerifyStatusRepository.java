package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.CompanyEVerifyStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEVerifyStatusRepository extends MongoRepository<CompanyEVerifyStatus, String> {
}
