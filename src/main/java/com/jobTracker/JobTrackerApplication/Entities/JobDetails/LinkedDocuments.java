package com.jobTracker.JobTrackerApplication.Entities.JobDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkedDocuments {
    private String documentId;
    private String documentName;
    private Instant uploadedDate;
    private List<String> jobsLinkedList;
    private String  documentType;
    private String originalFileName;

}
