package com.jobTracker.JobTrackerApplication.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class UserAccount {
    @Id
    private String userId;
    @Indexed
    private String email;
    private String password;
    private boolean active;
    private String signUpPlatform;
    private String firstName;
    private String lastName;

    private String userRoles;

    private boolean emailVerified;
    private boolean defaultResumeUploaded;



}
