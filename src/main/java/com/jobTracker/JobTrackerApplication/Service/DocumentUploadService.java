package com.jobTracker.JobTrackerApplication.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentUploadService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String APPLICATION_NAME = "JobTracker";
    private static HttpTransport httpTransport;

    private static final Map<String, String> MIME_TYPES = new HashMap<>();
    static {
        MIME_TYPES.put("pdf", "application/pdf");
        MIME_TYPES.put("doc", "application/msword");
        MIME_TYPES.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        MIME_TYPES.put("odf", "application/vnd.oasis.opendocument.text");
    }

    public String uploadFileToDrive(java.io.File file) throws IOException, GeneralSecurityException {
        String folderId = "1DoVCNhBycyR8OESS481R8Y-UJkeyVxmQ";
        Drive driveService = createDriveService();

        // File metadata
        File fileMetadata = new File();
        fileMetadata.setName(file.getName());
        fileMetadata.setParents(Collections.singletonList(folderId));

        // Determine the MIME type
        String mimeType = getMimeType(file.getName());

        // File content
        FileContent mediaContent = new FileContent(mimeType, file);

        // Upload file
        File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        return uploadedFile.getId();
    }

    private Drive createDriveService() throws IOException, GeneralSecurityException {
        InputStream serviceAccountStream = DocumentUploadService.class.getClassLoader().getResourceAsStream("credentials.json");
        if (serviceAccountStream == null) {
            throw new IOException("Service account key file not found in resources folder");
        }
        GoogleCredential credential = GoogleCredential.fromStream(serviceAccountStream)
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private String getMimeType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return MIME_TYPES.getOrDefault(extension.toLowerCase(), "application/octet-stream");
    }
}
