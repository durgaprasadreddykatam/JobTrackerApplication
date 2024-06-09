package com.jobTracker.JobTrackerApplication.Service;


import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
@Service
public class TextExtractor {
    public String extractText(File file,String extension) throws Exception {

        switch (extension) {
            case "pdf":
                return extractTextFromPDF(file);
            case "doc":
                return extractTextFromDOC(file);
            case "docx":
                return extractTextFromDOCX(file);
            case "odt":
                return extractTextFromODT(file);
            default:
                throw new IllegalArgumentException("Unsupported file type: " + extension);
        }
    }

    private String extractTextFromPDF(File file) throws IOException {
        PDDocument document = Loader.loadPDF(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    private String extractTextFromDOC(File file) throws IOException {
        String text;
        try (FileInputStream fis = new FileInputStream(file);
             HWPFDocument document = new HWPFDocument(fis);
             WordExtractor extractor = new WordExtractor(document)) {
            text = extractor.getText();
        }
        return text;

    }
    private String extractTextFromDOCX(File file) throws IOException {
        String text;
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis)) {
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            text = extractor.getText();

        }
        return text;
    }


    private String extractTextFromODT(File file) throws IOException, TikaException {
        Tika tika = new Tika();
        String text = tika.parseToString(file);
        return text;
    }




}
