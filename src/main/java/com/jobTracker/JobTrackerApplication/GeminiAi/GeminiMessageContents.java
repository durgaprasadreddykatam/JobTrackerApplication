package com.jobTracker.JobTrackerApplication.GeminiAi;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeminiMessageContents {
    private List<GeminiParts> parts;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GeminiParts {
        private String text;
    }
}
