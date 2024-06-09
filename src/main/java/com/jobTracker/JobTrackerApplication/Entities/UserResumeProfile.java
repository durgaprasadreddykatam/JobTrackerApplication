package com.jobTracker.JobTrackerApplication.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobTracker.JobTrackerApplication.Entities.ResumeDetails.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "profiles")
public class UserResumeProfile {
    @Id
    private String profileId;
    private String resumeId;
    private String roleAssociated;
    private ContactInformation contactInformation;
    @JsonProperty("ProfessionalSummary")
    private String professionalSummary;
    @JsonProperty("Education")
    private List<Education> educationList;
    @JsonProperty("WorkExperience")
    private List<WorkExperience> workExperiences;
    private List<String> targetTitles;
    private List<String> skills;
    @JsonProperty("Certification")
    private List<Certification> certifications;
    @JsonProperty("AwardsAndScholarship")
    private List<AwardsAndScholarship> awardsAndScholarships;
    @JsonProperty("Project")
    private List<Project> projects;
    @JsonProperty("VolunteeringAndLeadership")
    private List<VolunteeringAndLeadership> volunteeringAndLeaderships;
    @JsonProperty("Publication")
    private List<Publication> publications;
    private List<String>   interests;
    private List<String> keyWordsPresent;
    @JsonProperty("detectedRole")
    private String detectedRole;



}
