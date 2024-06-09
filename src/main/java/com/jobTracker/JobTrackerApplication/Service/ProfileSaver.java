package com.jobTracker.JobTrackerApplication.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobTracker.JobTrackerApplication.Entities.UserResumeProfile;
import org.springframework.stereotype.Service;

@Service
public class ProfileSaver {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public UserResumeProfile parseResumeResponse(String jsonResponse) throws Exception {
        String cleanJsonResponse = jsonResponse.replaceAll("```json\\n|\\n```", "").trim();
        UserResumeProfile resumeData = objectMapper.readValue(cleanJsonResponse, UserResumeProfile.class);

        return resumeData;
    }
}
