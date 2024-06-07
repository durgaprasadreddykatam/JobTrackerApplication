package com.jobTracker.JobTrackerApplication.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "jobs")
public class Jobs {
    @Id
    private String jobId;
    private String userId;

    private String jobRole;
    private String jobCompany;
    private String jobLocation;
    private long minimumSalary;
    private long maximumSalary;
    private String salaryCurrency;
    private String status;
    private Instant jobPostedDate;
    private Instant jobSavedDate;
    private Instant jobAppliedDate;
    private Instant jobInterviewDate;
    private Instant jobAcceptedDate;
    private Instant lastModifiedDate;
    private String jobUrl;
    private String jobSummary;

    public String getJobSummary() {
        return jobSummary;
    }

    public void setJobSummary(String jobSummary) {
        this.jobSummary = jobSummary;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getJobPostedDate() {
        return jobPostedDate;
    }

    public void setJobPostedDate(Instant jobPostedDate) {
        this.jobPostedDate = jobPostedDate;
    }

    public Instant getJobSavedDate() {
        return jobSavedDate;
    }

    public void setJobSavedDate(Instant jobSavedDate) {
        this.jobSavedDate = jobSavedDate;
    }

    public Instant getJobAppliedDate() {
        return jobAppliedDate;
    }

    public void setJobAppliedDate(Instant jobAppliedDate) {
        this.jobAppliedDate = jobAppliedDate;
    }

    public Instant getJobInterviewDate() {
        return jobInterviewDate;
    }

    public void setJobInterviewDate(Instant jobInterviewDate) {
        this.jobInterviewDate = jobInterviewDate;
    }

    public Instant getJobAcceptedDate() {
        return jobAcceptedDate;
    }

    public void setJobAcceptedDate(Instant jobAcceptedDate) {
        this.jobAcceptedDate = jobAcceptedDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public long getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(long minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public long getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(long maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
