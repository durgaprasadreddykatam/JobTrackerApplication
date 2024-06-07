package com.jobTracker.JobTrackerApplication.Entities;

import com.jobTracker.JobTrackerApplication.Entities.ResumeDetails.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "profiles")
public class UserProfile {
    @Id
    private String profileId;
    private String resumeId;
    private String roleAssociated;
    private ContactInformation contactInformation;
    private String  ProfessionalSummary;
    private List<Education> educationList;
    private List<WorkExperience> workExperiences;
    private List<String> targetTitles;
    private List<String> skills;
    private List<Certification> certifications;
    private List<AwardsAndScholarship> awardsAndScholarships;
    private List<Project> projects;
    private List<VolunteeringAndLeadership> volunteeringAndLeaderships;
    private List<Publication> publications;
    private List<String>   interests;



}
