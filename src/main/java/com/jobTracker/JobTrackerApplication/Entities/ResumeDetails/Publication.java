package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Publication {
    private String publicationName;
    private String publisher;
    private String date;
}
