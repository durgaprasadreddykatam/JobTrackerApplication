package com.jobTracker.JobTrackerApplication.GeminiAi;

import lombok.Data;
import java.util.List;

@Data
public class GeminiResponse {

    private UsageMetadata usageMetadata;
    private List<Candidates> candidates;

    @Data
    public static class UsageMetadata {
        private int promptTokenCount;
        private int candidatesTokenCount;
        private int totalTokenCount;
    }

    @Data
    public static class GeminiContent {
        private String role;
        private List<GeminiParts> parts;
    }

    @Data
    public static class GeminiParts {
        private String text;
    }

    @Data
    public static class SafetyRatings {
        private String category;
        private String probability;
    }

    @Data
    public static class Candidates {
        private GeminiContent content;
        private String finishReason;
        private int index;
        private List<SafetyRatings> safetyRatings;
    }
}
