package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Projections.DocumentProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends MongoRepository<Documents,String> {
    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'documentId' : 1, 'documentName' : 1, 'documentType' : 1, 'roleAssociated' : 1}")
    List<DocumentProjection> findAllByUserId(String userId);

}
