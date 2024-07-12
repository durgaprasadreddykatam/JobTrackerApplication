package com.jobTracker.JobTrackerApplication.KeywordChecker;

import com.jobTracker.JobTrackerApplication.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

@Service
public class KeywordCheckerService {
    @Autowired
    private KeywordLoader keywordLoader;

    @Autowired
    private DocumentRepository documentRepository;



    public Set<String> checkForKeywords(String text) {
        Set<String> matchedKeywords = new HashSet<>();
        Set<String> allKeywords = keywordLoader.getKeywords();
        for (String keyword : allKeywords) {
            String patternString = "\\b" + Pattern.quote(keyword) + "\\b";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                matchedKeywords.add(keyword);
            }
        }
        return matchedKeywords;
    }

    public Set<String> getAllKeyWords() throws IOException {
        return keywordLoader.getKeywords();
    }
    public Map<String,Set> getKeyWords(String jobDescription,String resume){
        Set<String> jobDescriptionKeywords = checkForKeywords(jobDescription);
//        String resumeText = documentRepository.findByDocumentId(documentId);
        Set<String> resumeKeywords = checkForKeywords(resume);
        Set<String> missingKeywords = new HashSet<>();
        for (String keyword : jobDescriptionKeywords) {
            if (!resumeKeywords.contains(keyword)) {
                missingKeywords.add(keyword);
            }
        }
        Map<String,Set> map = new HashMap<>();
        map.put("resumeKeywords",resumeKeywords);
        map.put("jobDescriptionKeywords",jobDescriptionKeywords);
        map.put("missingKeywords",missingKeywords);
        return map;


    }
}
