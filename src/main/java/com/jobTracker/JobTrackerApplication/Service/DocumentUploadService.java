package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Projections.DocumentProjection;
import com.jobTracker.JobTrackerApplication.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentUploadService {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    AuthUserAccountService authUserAccountService;

    public Documents uploadDocument(Documents document, MultipartFile file) throws IOException {
        document.setOriginalFileName(file.getOriginalFilename());
        document.setContentType(file.getContentType());
        document.setData(file.getBytes());
        return documentRepository.save(document);
    }

    public List<DocumentProjection> fetchDocumentsByUserId(String userId){
        List<DocumentProjection> documents = documentRepository.findAllByUserId(userId);
        return documents;

    }
}
