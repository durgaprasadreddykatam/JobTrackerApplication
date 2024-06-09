package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Projections.DocumentProjection;
import com.jobTracker.JobTrackerApplication.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentsService {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    AuthUserAccountService authUserAccountService;
    @Autowired
    TextExtractor textExtractor;
    @Autowired
    DocumentUploadService documentUploadService;

    public Documents uploadDocument(Documents document,MultipartFile file) throws Exception {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        File convertedFile = convertToFile(file);
        String fileId = documentUploadService.uploadFileToDrive(convertedFile);
        if(document.getDocumentType().equals("resume")) {
            document.setTextContent(textExtractor.extractText(convertedFile, fileExtension));
        }
        return documentRepository.save(document);
    }
    public Documents modifyDocument(Documents document) {
        return documentRepository.save(document);
    }
    public boolean deleteDocument(String documentId) {
        Optional<Documents> document = documentRepository.findById(documentId);
        if (document.isPresent()) {
            documentRepository.deleteById(documentId);
            return true;
        } else {
            return false;
        }
    }

    public List<DocumentProjection> fetchDocumentsByUserId(String userId){
        List<DocumentProjection> documents = documentRepository.findAllByUserId(userId);
        return documents;

    }
    public Documents fetchDocumentByDocumentId(String documentId){
        return documentRepository.findById(documentId).orElse(null);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
    public File convertToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        if (convertedFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
                fos.write(multipartFile.getBytes());
            }
        }
        return convertedFile;
    }

}
