package com.jobTracker.JobTrackerApplication.Projections;

public interface DocumentProjection {
    String getDocumentId();
    String getDocumentName();
    String getDocumentType();
    String getRoleAssociated();
}
