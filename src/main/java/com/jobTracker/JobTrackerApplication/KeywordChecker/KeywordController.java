package com.jobTracker.JobTrackerApplication.KeywordChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
public class KeywordController {
    @Autowired
    private KeywordCheckerService keywordCheckerService;


    @PostMapping("/check")
    public Map<String,Set> checkKeywords(@RequestBody Map<String,Object> jobDescriptionMap) {
        String jobDescription = (String)jobDescriptionMap.get("jobDescription");
        return keywordCheckerService.getKeyWords(jobDescription,(String)jobDescriptionMap.get("resume"));
    }

    @GetMapping("/getKeyWords")
    public Set<String> getKeyWordsFromJsonFile() throws IOException {
        return keywordCheckerService.getAllKeyWords();
    }
}


