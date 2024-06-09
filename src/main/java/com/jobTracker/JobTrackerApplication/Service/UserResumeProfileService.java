package com.jobTracker.JobTrackerApplication.Service;


import com.jobTracker.JobTrackerApplication.Entities.UserResumeProfile;
import com.jobTracker.JobTrackerApplication.GeminiAi.PromptConstants;
import com.jobTracker.JobTrackerApplication.GeminiAi.GeminiPromptService;
import com.jobTracker.JobTrackerApplication.GeminiAi.GeminiResponse;
import com.jobTracker.JobTrackerApplication.Repositories.UserResumeProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserResumeProfileService {
    @Autowired
    TextExtractor textExtractor;
    @Autowired
    GeminiPromptService geminiPromptService;
    @Autowired
    ProfileSaver profileSaver;
    @Autowired
    UserResumeProfileRepository userResumeProfileRepository;
    public String getResumePrompt() {
        return PromptConstants.RESUME_PROMPT;
    }



//    public UserResumeProfile parseResume(MultipartFile multipartFile,String resumeId,String roleAssociated) throws Exception {
//        String resumeText = textExtractor.extractText(multipartFile);
//        String finalPrompt = getResumePrompt().concat(resumeText);
//        String jsonResponse = geminiPromptService.geminiResponse(finalPrompt).getCandidates().get(0).getContent().getParts().get(0).getText();
//        UserResumeProfile userResumeProfile = profileSaver.parseResumeResponse(jsonResponse);
//        userResumeProfile.setResumeId(resumeId);
//        userResumeProfile.setRoleAssociated(roleAssociated);
//        userResumeProfileRepository.save(userResumeProfile);
//        return userResumeProfile;
//
//    }

}
