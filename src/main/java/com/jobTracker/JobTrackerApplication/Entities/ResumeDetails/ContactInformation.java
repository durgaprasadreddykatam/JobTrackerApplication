package com.jobTracker.JobTrackerApplication.Entities.ResumeDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ContactInformation {
    private  String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String linkedinUrl;
    private String githubUrl;
    private String address;

    private String city;
    private String state;
    private String websiteUrl;
}
