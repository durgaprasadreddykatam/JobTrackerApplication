package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.GeminiAi.GeminiRequest;
import com.jobTracker.JobTrackerApplication.GeminiAi.GeminiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GeminiPromptController {
    @Value("${gemini.api.model}")
    private String model;
    @Value("${gemini.api.key}")
    private String key;
    @Value("${gemini.api.url}")
    private String url;
    @Autowired
    private RestTemplate template;
    @GetMapping("/bot")
    public GeminiResponse geminiResponse(@RequestParam("prompt") String prompt){
        GeminiRequest request = new GeminiRequest(prompt);
        String completeUrl = url.concat(model).concat(":generateContent?key=").concat(key);
        GeminiResponse response = template.postForObject(completeUrl,request,GeminiResponse.class);
        return response;

    }
}
