package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WorkExperience {
    private String companyName;
    private String description;
    private String position;
    private String location;
    private String type;
    private String startDate;
    private String endDate;
    private boolean currentPosition;
}
