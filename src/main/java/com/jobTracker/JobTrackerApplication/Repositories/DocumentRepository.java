package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Entities.JobDetails.LinkedDocuments;
import com.jobTracker.JobTrackerApplication.Projections.DocumentProjection;
import lombok.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends MongoRepository<Documents,String> {


    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'documentId' : 1, 'documentName' : 1, 'documentType' : 1, 'roleAssociated' : 1}")
    List<DocumentProjection> findAllByUserId(String userId);

    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'documentId' : 1, 'documentName' : 1, 'uploadedDate' : 1, 'documentType' : 1,'jobsLinkedList': 1,'originalFileName': 1}")
    List<LinkedDocuments> findAllByUserId2(String userId);

    @Query(value = "{ '_id' : ?0 }", fields = "{ 'textContent' : 1, '_id': 0 }")
    String findByDocumentId(String documentId);

}
