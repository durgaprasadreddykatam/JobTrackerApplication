package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Configurations.JwtTokenGenerator;
import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Service.DocumentsService;
import com.jobTracker.JobTrackerApplication.Service.TextExtractor;
import com.jobTracker.JobTrackerApplication.Service.UserResumeProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*",allowCredentials = "true")
public class DocumentController {
    @Autowired
    DocumentsService documentsService;
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @GetMapping("/getDocuments")
    public ResponseEntity<Map<String,List>> getResumes(HttpServletRequest request){
        Map map =new HashMap();
        String userId =jwtTokenGenerator.getUserId();
        List documents = documentsService.fetchDocumentsByUserId(userId);
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
    public ResponseEntity<Map<String,Object>> uploadDocument(@RequestParam("file") MultipartFile file,
                                    @RequestParam("displayName") String displayName,
                                    @RequestParam("documentType") String documentType,
                                    @RequestParam("roleAssosiated") String roleAssosiated,
                                    @RequestParam(value = "otherRole", required = false) String otherRole)
            throws IOException {
        Map<String,Object> map =new HashMap<>();
        try {
            Documents documents = new Documents();
            documents.setUserId(jwtTokenGenerator.getUserId());
            documents.setDocumentName(displayName);
            documents.setDocumentType(documentType);
            documents.setUploadedDate(Instant.now());
            documents.setFileType(file.getContentType());
            documents.setOriginalFileName(file.getOriginalFilename());
            if ("other".equals(roleAssosiated)) {
                documents.setRoleAssociated(otherRole);
            } else {
                documents.setRoleAssociated(roleAssosiated);
            }
            Documents uploadedDocument = documentsService.uploadDocument(documents,file);
        }
        catch(Exception e){
            throw new IOException(e);
        }

        return new ResponseEntity<>(map,HttpStatus.OK);

    }
    @PostMapping("modifyDocument")
    public ResponseEntity modifyDocument(@RequestBody Map<String,Object> docMap) throws Exception {
        Documents document = documentsService.fetchDocumentByDocumentId((String)docMap.get("documentId"));
        if(document !=null){
            document.setModifiedDate(Instant.now());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("deleteDocument")
    public ResponseEntity modifyDocument(@RequestParam ("documentId") String documentId){
      boolean deleteDocument = documentsService.deleteDocument(documentId);
      if(deleteDocument) return new ResponseEntity<>(HttpStatus.OK);
      else return  new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



}
