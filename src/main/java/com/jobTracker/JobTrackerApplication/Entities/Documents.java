package com.jobTracker.JobTrackerApplication.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.File;
import java.time.Instant;
import java.util.List;

@Document(collection = "documents")
@Data
public class Documents {
    @Id
    private String documentId;
    private String userId;
    private String documentName;

    private String documentType;
    private String roleAssociated;
    private String textContent;
    private String originalFileName;
    private String fileType;
    private Instant uploadedDate;
    private Instant modifiedDate;
    private List<String> jobsLinkedList;


}
