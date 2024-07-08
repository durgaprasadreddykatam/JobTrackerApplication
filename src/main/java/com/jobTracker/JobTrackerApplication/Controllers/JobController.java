package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Configurations.JwtTokenGenerator;
import com.jobTracker.JobTrackerApplication.Entities.JobDetails.*;
import com.jobTracker.JobTrackerApplication.Entities.UserJobDetails;
import com.jobTracker.JobTrackerApplication.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "chrome-extension://bojpmijggdcoalhainhdlgcbiidbffof"}, allowedHeaders = "*", allowCredentials = "true")
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;
    @PostMapping("/addJob")
    private ResponseEntity<String> addJob(@RequestBody  Map<String,Object> jobMap){
        System.out.println(jobMap);
        Company company = new Company();
        Job jobDetails = new Job();
        UserJobDetails userJobDetails = new UserJobDetails();

        if (jobMap.containsKey("savedFromExtension") && jobMap.get("savedFromExtension") != null ) {
            company.setAddedFromExtension((Boolean) jobMap.get("savedFromExtension"));
            jobDetails.setSavedFromExtension((Boolean) jobMap.get("savedFromExtension"));
        }

        if (jobMap.containsKey("companyWebsite") && jobMap.get("companyWebsite") != null ) {
            company.setWebsite((String) jobMap.get("companyWebsite"));
        }
        if (jobMap.containsKey("companyName") && jobMap.get("companyName")!= null ) {
            company.setName((String) jobMap.get("companyName"));
        }
        if (jobMap.containsKey("companyLogoUrl") && jobMap.get("companyLogoUrl")!= null ) {
            company.setImageSource((String) jobMap.get("companyLogoUrl"));
        }
        if (jobMap.containsKey("companyCategory") && jobMap.get("companyCategory") != null) {
            company.setIndustry((String) jobMap.get("companyCategory"));
        }
        if (jobMap.containsKey("minEmployees") && jobMap.get("minEmployees") != null ) {
            company.setMinEmployees((Integer) jobMap.get("minEmployees"));
        }
        if (jobMap.containsKey("maxEmployees") && jobMap.get("maxEmployees") != null ) {
            company.setMaxEmployees((Integer) jobMap.get("maxEmployees"));
        }
        if (jobMap.containsKey("companyDescription") && jobMap.get("companyDescription") != null ) {
            company.setDescription((String) jobMap.get("companyDescription"));
        }

        if (jobMap.containsKey("companyLocation") && jobMap.get("companyLocation") != null ) {
            company.setHeadQuarters((String) jobMap.get("companyLocation"));
        }
        if (jobMap.containsKey("companyFounded") && jobMap.get("companyFounded") != null ) {
            company.setFounded((String) jobMap.get("companyFounded"));
        }
        if(company.hasAtLeastOneField()){
            String savedCompanyId = jobService.saveCompany(company).getCompanyId();
            jobDetails.setCompanyId(savedCompanyId);
        }


        if (jobMap.containsKey("jobUrl") && jobMap.get("jobUrl") != null ) {
            jobDetails.setJobUrl((String) jobMap.get("jobUrl"));
        }
        if (jobMap.containsKey("jobRole") && jobMap.get("jobRole") != null ) {
            jobDetails.setJobRole((String) jobMap.get("jobRole"));
        }
        if (jobMap.containsKey("location") && jobMap.get("location") != null ) {
            jobDetails.setJobLocation((String) jobMap.get("location"));
        }
        if (jobMap.containsKey("minimumSalary") && jobMap.get("minimumSalary") != null ) {
            jobDetails.setMinimumSalary((String) jobMap.get("minimumSalary"));
        }
        if (jobMap.containsKey("maximumSalary") && jobMap.get("maximumSalary") != null ) {
            jobDetails.setMaximumSalary((String) jobMap.get("maximumSalary"));
        }
        if (jobMap.containsKey("salaryCurrency") && jobMap.get("salaryCurrency") != null ) {
            jobDetails.setSalaryCurrency((String) jobMap.get("salaryCurrency"));
        }
        if (jobMap.containsKey("jobSummaryHTML") && jobMap.get("jobSummaryHTML") != null ) {
            jobDetails.setJobSummary((String) jobMap.get("jobSummaryHTML"));
        }
        if (jobMap.containsKey("savedWebsite") && jobMap.get("savedWebsite") != null ) {
            jobDetails.setAddedPlatform((String) jobMap.get("savedWebsite"));
        }
        //  TODO: update this  after modifying json Object
        if (jobMap.containsKey("postedDate") && jobMap.get("postedDate") != null ) {
            String date = (String) jobMap.get("postedDate");
            jobDetails.setJobPostedDate(Instant.parse(date));
        }
        String savedJobId = jobService.saveJob(jobDetails).getJobId();
        userJobDetails.setJobId(savedJobId);
        if(jobMap.containsKey("status") && jobMap.get("status") != null ){
            userJobDetails.setStatus((String)jobMap.get("status"));
        }else{
            userJobDetails.setStatus("Saved");
        }
        userJobDetails.setUserId(jwtTokenGenerator.getUserId());
        userJobDetails.setJobSavedDate(Instant.now());
        return new ResponseEntity<>(jobService.saveUserJobDetails(userJobDetails), HttpStatus.OK);

    }

    @GetMapping("/getJobs")
    private ResponseEntity<Map<String,Object>> fetchJobsForUser(){
        Map jobsMap = new HashMap();
        String userId = jwtTokenGenerator.getUserId();
        jobsMap.put("jobs",jobService.getUserJobsList(userId));
        return new ResponseEntity<>(jobsMap, HttpStatus.OK);
    }
    @GetMapping("/getCompleteJobDetails")
    private ResponseEntity<Map<String, Object>> getCompleteJobDetails(@RequestParam String entityId)  {
        Map<String, Object> responseMap = jobService.getJobByJobId(entityId);
        if(!responseMap.isEmpty()){
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/modifyJobStatus")
    private ResponseEntity<String> modifyJobStatus(@RequestBody Map<String,Object> map){
        String jobId = jobService.modifyJobStatus((String)map.get("entityId"),(String)map.get("status"));
        if(!jobId.equals("invalid EntityId")){
            return new ResponseEntity<>("Updated "+jobId+(String)map.get("status"),HttpStatus.OK);
        }
        else return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteJob")
    private ResponseEntity<String> deleteJob(@RequestParam String entityId){
        String message = jobService.deleteJobByEntityId(entityId);
        if(!message.equals("invalid EntityId")){
            return new ResponseEntity<>("Successfully Deleted ",HttpStatus.OK);
        }
        else return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }

   @PostMapping("/addNote")
    private ResponseEntity<UserJobDetails> addNote(@RequestBody  Map<String,Object> objectMap){
        Note note =new Note();
        note.setNoteText((String) objectMap.get("noteText"));
        note.setNoteCreatedBy(jwtTokenGenerator.getUserName());
       UserJobDetails message = jobService.createNewNote((String) objectMap.get("entityId"),note);
        if(!message.equals("invalid EntityId")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/editNote")
    private ResponseEntity<UserJobDetails> editNote(@RequestBody  Map<String,Object> objectMap){
        System.out.println(objectMap);
        Map<String,Object> noteObject = (Map<String, Object>) objectMap.get("note");
        Note note = new Note((Integer) noteObject.get("noteIndex"),(String) noteObject.get("noteText"),(String) noteObject.get("noteCreatedBy"),Instant.parse((String)noteObject.get("noteCreationDate")));
        UserJobDetails userJobDetails = jobService.editNote((String) objectMap.get("entityId"),note);
        if(userJobDetails != null){
            return new ResponseEntity<>(userJobDetails,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/deleteNote")
    private ResponseEntity<UserJobDetails> deleteNote(@RequestBody  Map<String,Object> objectMap){
        System.out.println(objectMap);
        Map<String,Object> noteObject = (Map<String, Object>) objectMap.get("note");
        Note note = new Note((Integer) noteObject.get("noteIndex"),(String) noteObject.get("noteText"),(String) noteObject.get("noteCreatedBy"),Instant.parse((String)noteObject.get("noteCreationDate")));
        UserJobDetails userJobDetails = jobService.deleteNote((String) objectMap.get("entityId"),note);
        if(userJobDetails != null){
            return new ResponseEntity<>(userJobDetails,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/addTask")
    private ResponseEntity<UserJobDetails> addTask(@RequestBody  Map<String,Object> objectMap){
        Task task = new Task();
        task.setTaskName((String) objectMap.get("taskName"));
        task.setTaskStatus((String) objectMap.get("taskStatus"));
        UserJobDetails userJobDetails = jobService.createNewTask((String) objectMap.get("entityId"),task);
        if(userJobDetails != null){
            return new ResponseEntity<>(userJobDetails,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/editTask")
    private ResponseEntity<UserJobDetails> editTask(@RequestBody  Map<String,Object> objectMap){
        System.out.println(objectMap);
        Map<String,Object> taskObject = (Map<String, Object>) objectMap.get("task");
        Task task = new Task((Integer) taskObject.get("taskIndex"),(Integer) taskObject.get("timelineIndex"),(String) taskObject.get("taskName"),(String) taskObject.get("taskStatus"));
        UserJobDetails userJobDetails = jobService.editTask((String) objectMap.get("entityId"),task);
        if(userJobDetails != null){
            return new ResponseEntity<>(userJobDetails,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/deleteTask")
    private ResponseEntity<String> deleteTask(@RequestBody  Map<String,Object> objectMap){

//        String message = jobService.deleteTask((String) objectMap.get("entityId"),
//                new Task((Integer) objectMap.get("taskIndex"),(String) objectMap.get("taskName"),(String) objectMap.get("taskStatus")));;
//        if(!message.equals("invalid EntityId")){
//            return new ResponseEntity<>("Successfully deleted Task ",HttpStatus.OK);
//        }
//        else return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
        return null;
    }

    @PostMapping("/linkDocument")
    private ResponseEntity<UserJobDetails> linkDocument(@RequestBody  Map<String,Object> objectMap){
        Map<String,Object> document = (Map<String, Object>) objectMap.get("document");
        LinkedDocuments linkedDocument = new LinkedDocuments();
        linkedDocument.setDocumentId((String)document.get("documentId"));
        linkedDocument.setDocumentName((String)document.get("documentName"));
        linkedDocument.setDocumentType((String)document.get("documentType"));
        linkedDocument.setUploadedDate(Instant.parse((String)document.get("uploadedDate")));
        linkedDocument.setOriginalFileName((String)document.get("originalFileName"));
        UserJobDetails userJobDetails = jobService.linkDocument((String)objectMap.get("entityId"),linkedDocument, jwtTokenGenerator.getUserName());
        if(userJobDetails != null){
            return new ResponseEntity<>(userJobDetails,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/unLinkDocument")
    private ResponseEntity<UserJobDetails> unLinkDocument(@RequestBody  Map<String,Object> objectMap){
        UserJobDetails userJobDetails = jobService.unLinkDocument((String)objectMap.get("entityId"),(String)objectMap.get("documentId"), jwtTokenGenerator.getUserName());
        if(userJobDetails != null){
            return new ResponseEntity<>(userJobDetails,HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }


}
