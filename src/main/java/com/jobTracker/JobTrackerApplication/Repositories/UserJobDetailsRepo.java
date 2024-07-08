package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.UserJobDetails;
import lombok.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserJobDetailsRepo extends MongoRepository<UserJobDetails,String> {
    @Data
    class jobCard{
        String entityId;
        String jobId;
        String status;
        Instant jobSavedDate;
        String jobRole;
        String companyId;
        String companyName;
        String companyImageUrl;
    }

    Integer countUserJobDetailsByJobIdAndUserId(String jobId,String UserId);
    List<UserJobDetails> findAllByJobIdAndUserId(String jobId,String UserId);

    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'entityId' : 1, 'jobId' : 1, 'status' : 1, 'jobSavedDate' : 1}")
    List<jobCard> findAllByUserId(String userId);
    Integer countByUserId(String userId);

    Integer countByEntityId(String entityId);
    UserJobDetails getByEntityId(String entityId);

}
