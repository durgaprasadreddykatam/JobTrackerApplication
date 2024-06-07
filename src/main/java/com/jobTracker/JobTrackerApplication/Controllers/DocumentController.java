package com.jobTracker.JobTrackerApplication.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*",allowCredentials = "true")
public class ResumesController {

    @GetMapping("/resumes")
    public String getResumes(HttpServletRequest request){
        return "Sucess";

    }
    @GetMapping("/uploadDocument")
    public String uploadDocument(@RequestBody Map<String,Object> docMap ){
        File file = (File)docMap.get("file");
        String  documentType = (String)docMap.get("documentType");
        String  displayName = (String)docMap.get("displayName");
        String  roleAssociated = (String)docMap.get("roleAssociated");
        String  otherRole = (String)docMap.get("otherRole");
        return "Success";

    }
}
