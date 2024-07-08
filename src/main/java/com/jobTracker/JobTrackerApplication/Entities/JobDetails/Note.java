package com.jobTracker.JobTrackerApplication.Entities.JobDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    public int noteIndex;
    public String noteText;
    public String noteCreatedBy;
    public Instant noteCreationDate;

}
