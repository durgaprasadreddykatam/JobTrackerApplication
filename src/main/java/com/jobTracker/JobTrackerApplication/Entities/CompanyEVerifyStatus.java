package com.jobTracker.JobTrackerApplication.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
@Data
@Document(collection = "everifiedcompanies")
public class CompanyEVerifyStatus {
    @Id
    private String eVerifyCompanyId;
    private String companyName;
    private String doingBusinessAs;
    private String primaryIndustryType;
    private String accountStatus;
    private Instant dateEnrolled;
    private Instant  dateTerminated;
    private String workForceSize;
    private int noOfWorkingSites;
    private List<String> hiringSiteLocations;

}
