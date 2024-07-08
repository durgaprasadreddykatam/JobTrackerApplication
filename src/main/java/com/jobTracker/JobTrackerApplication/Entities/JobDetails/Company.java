package com.jobTracker.JobTrackerApplication.Entities.JobDetails;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "company")
public class Company {
    @Id
    private String companyId;
    private String name;
    private String website;
    private String industry;
    private int minEmployees;
    private int maxEmployees;
    private String headQuarters;
    private String imageSource;
    private String description;
    private String founded;
    private boolean addedFromExtension;

    public boolean hasAtLeastOneField() {
        return (name != null && !name.isEmpty()) ||
                (website != null && !website.isEmpty()) ||
                (industry != null && !industry.isEmpty()) ||
                (minEmployees > 0) ||
                (maxEmployees > 0) ||
                (headQuarters != null && !headQuarters.isEmpty()) ||
                (imageSource != null && !imageSource.isEmpty()) ||
                (description != null && !description.isEmpty()) ||
                (founded != null && !founded.isEmpty());
    }


}
