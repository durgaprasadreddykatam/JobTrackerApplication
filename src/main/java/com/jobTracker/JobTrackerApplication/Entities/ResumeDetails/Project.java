package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Project {
    private String projectName;
    private String organisation;
    private String startDate;
    private String endDate;
    private String AdditionalInformation;
}
