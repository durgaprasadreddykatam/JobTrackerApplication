package com.jobTracker.JobTrackerApplication.KeywordChecker;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class KeywordLoader {
    private Set<String> keywords;
    @Autowired
    private ResourceLoader resourceLoader;
    @PostConstruct
    public void loadKeywords() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/skills.json");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = mapper.getFactory().createParser(inputStream);
        KeywordFile keywordFile = mapper.readValue(jsonParser, KeywordFile.class);
        this.keywords = keywordFile.getSkills().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    public Set<String> getKeywords(){
        return this.keywords;
    }

    @Data
    public static class KeywordFile {
        private List<String> skills;
//        private List<String> technologies;
    }
}
