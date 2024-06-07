package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Configurations.JwtTokenGenerator;
import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Service.DocumentUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*",allowCredentials = "true")
public class DocumentController {
    @Autowired
    DocumentUploadService documentUploadService;
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @GetMapping("/getDocuments")
    public ResponseEntity<Map<String,List>> getResumes(HttpServletRequest request){
        Map map =new HashMap();
        String userId =jwtTokenGenerator.getUserId();
        List documents = documentUploadService.fetchDocumentsByUserId(userId);
        if(documents.isEmpty()){
            map.put("user have Not Uploaded Yet",documents);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else{
            map.put("documentList",documents);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

    }
    @PostMapping("/uploadDocument")
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                    @RequestParam("displayName") String displayName,
                                    @RequestParam("documentType") String documentType,
                                    @RequestParam("roleAssosiated") String roleAssosiated,
                                    @RequestParam(value = "otherRole", required = false) String otherRole)
            throws IOException {
        System.out.println("Request received to this uploadDocument End Point");
        try {
            Documents documents = new Documents();
            documents.setUserId(jwtTokenGenerator.getUserId());
            documents.setDocumentName(displayName);
            documents.setDocumentType(documentType);
            if ("other".equals(roleAssosiated)) {
                documents.setRoleAssociated(otherRole);
            } else {
                documents.setRoleAssociated(roleAssosiated);
            }

            documentUploadService.uploadDocument(documents, file);
        }
        catch(Exception e){
            throw new IOException(e);
        }

        return "Success";

    }


}
