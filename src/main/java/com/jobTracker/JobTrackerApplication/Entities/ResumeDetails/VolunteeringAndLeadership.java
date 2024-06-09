package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class VolunteeringAndLeadership {
    private String organisation;
    private String involvement;
    private String city;
    private String state;
    private String startDate;
    private String endDate;
    private String additionalInformation;
}
