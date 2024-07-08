package com.jobTracker.JobTrackerApplication.Entities.JobDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int taskIndex;
    private int timelineIndex;
    private String  taskName;
    private String  taskStatus;
}
