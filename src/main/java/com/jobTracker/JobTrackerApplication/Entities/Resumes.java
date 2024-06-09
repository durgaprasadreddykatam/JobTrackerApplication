package com.jobTracker.JobTrackerApplication.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resumes")
@Data
public class Resumes {

    @Id
    private String resumeId;
    private String resumeName;
    private boolean defaultResume;



}
