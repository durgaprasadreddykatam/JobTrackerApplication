package com.jobTracker.JobTrackerApplication.GeminiAi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class GeminiRequest {
    private GenerationConfig generationConfig;
    private List<GeminiMessageContents> contents;

    public GeminiRequest(String prompt, String responseMimeType) {
        this.generationConfig = new GenerationConfig(responseMimeType);
        this.contents = new ArrayList<>();
        GeminiMessageContents messageContent = new GeminiMessageContents();
        List<GeminiMessageContents.GeminiParts> partsList = new ArrayList<>();
        partsList.add(new GeminiMessageContents.GeminiParts(prompt));
        messageContent.setParts(partsList);
        this.contents.add(messageContent);
    }
    public GeminiRequest(String prompt) {
        this.contents = new ArrayList<>();
        GeminiMessageContents messageContent = new GeminiMessageContents();
        List<GeminiMessageContents.GeminiParts> partsList = new ArrayList<>();
        partsList.add(new GeminiMessageContents.GeminiParts(prompt));
        messageContent.setParts(partsList);
        this.contents.add(messageContent);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GenerationConfig {
        private String response_mime_type;
    }
}
