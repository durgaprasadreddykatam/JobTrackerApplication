package com.jobTracker.JobTrackerApplication.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resumes")
public class Resumes {

    @Id
    private String resumeId;
    private String resumeName;
    private boolean defaultResume;
    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public boolean isDefaultResume() {
        return defaultResume;
    }

    public void setDefaultResume(boolean defaultResume) {
        this.defaultResume = defaultResume;
    }


}
