package com.jobTracker.JobTrackerApplication.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "jobs")
@Data
public class Jobs {
    @Id
    private String jobId;
    private String userId;

    private String jobRole;
    private String jobCompany;
    private String jobLocation;
    private long minimumSalary;
    private long maximumSalary;
    private String salaryCurrency;
    private String status;
    private Instant jobPostedDate;
    private Instant jobSavedDate;
    private Instant jobAppliedDate;
    private Instant jobInterviewDate;
    private Instant jobAcceptedDate;
    private Instant lastModifiedDate;
    private String jobUrl;
    private String jobSummary;


}
