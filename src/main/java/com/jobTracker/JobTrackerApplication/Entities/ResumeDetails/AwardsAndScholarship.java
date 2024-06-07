package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AwardsAndScholarship {
    private String awardName;
    private String organisation;
    private String awardDate;
}
