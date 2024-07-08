package com.jobTracker.JobTrackerApplication.Entities;

import com.jobTracker.JobTrackerApplication.Entities.JobDetails.LinkedDocuments;
import com.jobTracker.JobTrackerApplication.Entities.JobDetails.Note;
import com.jobTracker.JobTrackerApplication.Entities.JobDetails.Task;
import com.jobTracker.JobTrackerApplication.Entities.JobDetails.TimeLine;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "user-jobs")
public class UserJobDetails {
    @Id
    private String entityId;
    private String jobId;
    private String userId;
    private String status;
    private List jobTags; //TODO: adding tags to be implemented in JobController
    private Instant jobSavedDate;
    private Instant jobAppliedDate;
    private Instant jobInterviewDate;
    private Instant jobAcceptedDate;
    private List<LinkedDocuments> linkedDocumentsList;
    private List<Note> notesList;
    private List<Task> tasksList;
    private List<TimeLine> jobTimelineList;
    private int notesCounter;
    private int taskCounter;
}
