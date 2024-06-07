package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Education {
    private String school;
    private String location;
    private String gpa;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;
    private String description;
}
