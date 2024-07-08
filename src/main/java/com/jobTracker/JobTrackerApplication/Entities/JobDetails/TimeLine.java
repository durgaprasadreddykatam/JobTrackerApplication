package com.jobTracker.JobTrackerApplication.Entities.JobDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class TimeLine {
    private int timelineIndex;
    private Instant time;
    private String type;
    private String modificationHeading;
    private String modificationDescription;

}
