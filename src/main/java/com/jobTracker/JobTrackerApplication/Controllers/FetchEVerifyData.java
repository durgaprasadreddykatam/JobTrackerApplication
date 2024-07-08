package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Entities.CompanyEVerifyStatus;
import com.jobTracker.JobTrackerApplication.Service.CompanyEVerifyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/fetchEVerifyData")
public class FetchEVerifyData {
//
//    @Value("${everify.link}")
//    private String url;
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    CompanyEVerifyService eVerifyService;
//    String[] industryArray = {
//            "ACCOMMODATION AND FOOD SERVICES",
//            "ADMINISTRATIVE AND SUPPORT AND WASTE MANAGEMENT AND REMEDIATION SERVICES",
//            "AGRICULTURE, FORESTRY, FISHING AND HUNTING",
//            "ARTS, ENTERTAINMENT, AND RECREATION",
//            "CONSTRUCTION",
//            "EDUCATIONAL SERVICES",
//            "FINANCE AND INSURANCE",
//            "HEALTH CARE AND SOCIAL ASSISTANCE",
//            "INFORMATION",
//            "MANAGEMENT OF COMPANIES AND ENTERPRISES",
//            "MANUFACTURING",
//            "MINING, QUARRYING, AND OIL AND GAS EXTRACTION",
//            "OTHER SERVICES (EXCEPT PUBLIC ADMINISTRATION)",
//            "PROFESSIONAL, SCIENTIFIC, AND TECHNICAL SERVICES",
//            "PUBLIC ADMINISTRATION",
//            "REAL ESTATE AND RENTAL AND LEASING",
//            "RETAIL TRADE",
//            "TRANSPORTATION AND WAREHOUSING",
//            "UTILITIES",
//            "WHOLESALE TRADE"
//    };
//
//
//    @RequestMapping("/fetchByPageNumber")
//    public String fetchByPageNumber(@RequestParam int pageNumber) {
//        String completeUrl = url.concat(String.valueOf(pageNumber));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.ALL)); // Accept any content type
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        try {
//            ResponseEntity<String> response = restTemplate.exchange(
//                    completeUrl,
//                    HttpMethod.GET,
//                    entity,
//                    String.class
//            );
//            String htmlContent = response.getBody();
//            String message= eVerifyService.addListToDb(parseHtml(htmlContent));
//            return message;
//        } catch (RestClientException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    @RequestMapping("/fetchBetweenPageNumbers")
//    public void fetchBetweenPageNumbers(@RequestParam int startPageNumber, @RequestParam int endPageNumber) {
//        for (int i = startPageNumber; i <= endPageNumber; i++) {
//            String completeUrl = url.concat(String.valueOf(i));
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.ALL)); // Accept any content type
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            try {
//                ResponseEntity<String> response = restTemplate.exchange(
//                        completeUrl,
//                        HttpMethod.GET,
//                        entity,
//                        String.class
//                );
//                String htmlContent = response.getBody();
//                String message= eVerifyService.addListToDb(parseHtml(htmlContent));
//                System.out.println("message for pageNo"+":"+i+":"+message);
//            } catch (RestClientException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @RequestMapping("/fetchBetweenPageNumbersAndIndustry")
//    public void fetchBetweenPageNumbersAndAddIndustry(@RequestParam int startPageNumber, @RequestParam int endPageNumber,
//                                                      @RequestParam int industryNumber) {
//        if(industryNumber >0 && industryNumber < 21){
//            String industry = industryArray[industryNumber-1];
//            System.out.println(industry);
//            for (int i = startPageNumber; i <= endPageNumber; i++) {
//                String completeUrl = url.concat(String.valueOf(industryNumber))
//                        .concat("&field_account_status_value=Open&items_per_page=50&page=")
//                        .concat(String.valueOf(i));
//                HttpHeaders headers = new HttpHeaders();
//                headers.setAccept(Arrays.asList(MediaType.ALL));
//                HttpEntity<String> entity = new HttpEntity<>(headers);
//
//                try {
//                    ResponseEntity<String> response = restTemplate.exchange(
//                            completeUrl,
//                            HttpMethod.GET,
//                            entity,
//                            String.class
//                    );
//                    String htmlContent = response.getBody();
//                    String message= eVerifyService.addListToDb(parseHtml(htmlContent,industry));
//                    System.out.println("message for pageNo"+":"+i+":"+message);
//                } catch (RestClientException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    private List<CompanyEVerifyStatus> parseHtml(String htmlContent,String industry) {
//        List<CompanyEVerifyStatus> companyEVerifyStatuses = new ArrayList<>();
//
//        Document document = Jsoup.parse(htmlContent);
//        Elements rows = document.select("table tbody tr");
//
//        for (Element row : rows) {
//            Elements columns = row.select("td");
//            if (columns.size() >= 8) {
//                CompanyEVerifyStatus status = new CompanyEVerifyStatus();
//                status.setCompanyName(columns.get(0).text());
//                status.setAccountStatus(columns.get(2).text());
//                status.setDoingBusinessAs(columns.get(1).text());
//                status.setPrimaryIndustryType(industry);
//
//                String dateEnrolled = columns.get(3).text();
//                status.setDateEnrolled(parseDate(dateEnrolled));
//
//                String dateTerminated = columns.get(4).text();
//                status.setDateTerminated(parseDate(dateTerminated));
//
//                String[] locationsArray = columns.get(7).text().split(",");
//                ArrayList<String> locationsList = new ArrayList<>(Arrays.asList(locationsArray));
//                for (int i = 0; i < locationsList.size(); i++) {
//                    locationsList.set(i, locationsList.get(i).trim());
//                }
//                status.setHiringSiteLocations(locationsList);
//
//                status.setWorkForceSize(columns.get(5).text());
//                String noOfWorkingSites = columns.get(6).text();
//                if (!noOfWorkingSites.isEmpty()) {
//                    status.setNoOfWorkingSites(Integer.parseInt(noOfWorkingSites));
//                }
//
//                companyEVerifyStatuses.add(status);
//            }
//        }
//        return companyEVerifyStatuses;
//    }
//
//    private Instant parseDate(String dateString) {
//        if (dateString.isEmpty() || dateString.equals("")) {
//            return null;
//        }
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDate date = LocalDate.parse(dateString, formatter);
//        LocalDateTime dateTime = date.atStartOfDay();
//        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
//        return instant;
//    }
}
