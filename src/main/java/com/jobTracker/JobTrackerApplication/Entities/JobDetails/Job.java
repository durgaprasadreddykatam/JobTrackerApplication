package com.jobTracker.JobTrackerApplication.Entities.JobDetails;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "jobs")
@Data
public class Job {
    @Id
    private String jobId;

    private String jobRole;
    private String jobLocation;
    private String minimumSalary;
    private String maximumSalary;
    private String salaryCurrency;

    private Instant jobPostedDate;
    private String jobUrl;
    private String jobSummary;

    private String companyId;
    private String addedPlatform;
    private boolean savedFromExtension;



}
