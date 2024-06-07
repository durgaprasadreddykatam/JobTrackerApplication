package com.jobTracker.JobTrackerApplication.GeminiAi;

import lombok.*;

import java.lang.reflect.Array;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeminiMessage {

    private String prompt;
    private String inputData;
}
