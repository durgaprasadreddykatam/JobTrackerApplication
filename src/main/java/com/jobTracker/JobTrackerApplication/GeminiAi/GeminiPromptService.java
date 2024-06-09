package com.jobTracker.JobTrackerApplication.GeminiAi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiPromptService {
    @Value("${gemini.api.model}")
    private String model;
    @Value("${gemini.api.key}")
    private String key;
    @Value("${gemini.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;
    public GeminiResponse geminiResponse(String prompt){
        GeminiRequest request = new GeminiRequest(prompt);
        String completeUrl = url.concat(model).concat(":generateContent?key=").concat(key);
        GeminiResponse response = template.postForObject(completeUrl,request,GeminiResponse.class);
        return response;

    }
}
