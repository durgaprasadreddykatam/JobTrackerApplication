package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Certification {
    private String certificationName;
    private String certificationProvider;
    private String certificationLink;
    private String certificationValidityStart;
    private String certificationValidityEnd;
}
