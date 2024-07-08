package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.Documents;
import com.jobTracker.JobTrackerApplication.Entities.JobDetails.*;
import com.jobTracker.JobTrackerApplication.Entities.UserJobDetails;
import com.jobTracker.JobTrackerApplication.Repositories.CompanyRepository;
import com.jobTracker.JobTrackerApplication.Repositories.DocumentRepository;
import com.jobTracker.JobTrackerApplication.Repositories.JobRepository;
import com.jobTracker.JobTrackerApplication.Repositories.UserJobDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserJobDetailsRepo userJobDetailsRepository;
    @Autowired
    private DocumentRepository documentRepository;



    public Company saveCompany(Company company){
        if(company.getWebsite() == null ){
            System.out.println("website is null");
            return companyRepository.save(company);
        }else{
            int count = companyRepository.countAllByWebsiteAndAddedFromExtensionTrue(company.getWebsite());
            System.out.println(count);
            if(count == 0){
                return companyRepository.save(company);
            }else {
                return companyRepository.getAllByWebsiteAndAddedFromExtensionTrue(company.getWebsite()).get(0);
            }
        }

    }

    public Job saveJob(Job job){
        if(job.getJobUrl() == null){
            System.out.println("job url is null");
            return jobRepository.save(job);
        }else{
            int count = jobRepository.countByJobUrlAndSavedFromExtensionTrue(job.getJobUrl());
            System.out.println(count);
            if(count == 0){
                return jobRepository.save(job);
            }
            else{
                return jobRepository.findAllByJobUrlAndSavedFromExtensionTrue(job.getJobUrl()).get(0);
            }
        }

    }

    public String saveUserJobDetails(UserJobDetails jobDetails){
        int count = userJobDetailsRepository.countUserJobDetailsByJobIdAndUserId(jobDetails.getJobId(), jobDetails.getUserId());
        System.out.println(count);
        if(count == 0){
            jobDetails.setJobTimelineList(Collections.singletonList(new TimeLine(
                    0,Instant.now(),"addJob", "New Job Created", "You Added a new Job"
            )));
            jobDetails.setTasksList(new ArrayList<>());
            jobDetails.setNotesList(new ArrayList<>());
            jobDetails.setLinkedDocumentsList(new ArrayList<>());
            jobDetails.setNotesCounter(0);
            jobDetails.setTaskCounter(0);
            return userJobDetailsRepository.save(jobDetails).getEntityId();
        }
        else{
            return "Job Already saved By User";
        }
    }

    public List getUserJobsList(String userId){
        int count = userJobDetailsRepository.countByUserId(userId);
        List<UserJobDetailsRepo.jobCard> jobList = new ArrayList();
        if(count >0){
            jobList = userJobDetailsRepository.findAllByUserId(userId);
            return jobList.stream().map(userJob->{
                Job job = jobRepository.findByJobId(userJob.getJobId());
                if(job !=null){
                    userJob.setJobRole(job.getJobRole());
                    userJob.setCompanyId(job.getCompanyId());
                    Company company = companyRepository.findByCompanyId(userJob.getCompanyId());
                    if(company!=null){
                        userJob.setCompanyName(company.getName());
                        userJob.setCompanyImageUrl(company.getImageSource());
                    }
                }
                return userJob;
            }).collect(Collectors.toList());
        }else {
            return jobList;
        }
    }

    public Map<String, Object> getJobByJobId(String entityId){
        int count = userJobDetailsRepository.countByEntityId(entityId);
        Map<String, Object> combinedDetails = new HashMap<>();
        if(count>0){
            UserJobDetails userJobDetails = userJobDetailsRepository.getByEntityId(entityId);
            Job jobDetails = jobRepository.findByJobId(userJobDetails.getJobId());
            Company companyDetails = companyRepository.findByCompanyId(jobDetails.getCompanyId());

            combinedDetails.put("userJobDetails",userJobDetails);
            combinedDetails.put("jobDetails",jobDetails);
            combinedDetails.put("companyDetails",companyDetails);
        }
        return combinedDetails;
    }
    public String modifyJobStatus(String EntityId,String status){
        Optional<UserJobDetails> optionalEntry = userJobDetailsRepository.findById(EntityId);
        if(optionalEntry.isPresent()){
            UserJobDetails jobDetails = optionalEntry.get();
            String oldStatus = jobDetails.getStatus();
            jobDetails.setStatus(status);
            List<TimeLine> timelineList = jobDetails.getJobTimelineList();
            timelineList.add(new TimeLine(timelineList.size(),Instant.now(),"statusUpdate", "Moved to ".concat(status), "You Moved this Job from ".concat(oldStatus).concat(" to").concat(oldStatus)));
            userJobDetailsRepository.save(jobDetails);
            return jobDetails.getEntityId();
        }else{
            return "invalid EntityId";
        }
    }
    public String deleteJobByEntityId(String EntityId){
        Optional<UserJobDetails> optionalEntry = userJobDetailsRepository.findById(EntityId);
        if(optionalEntry.isPresent()){
            UserJobDetails jobDetails = optionalEntry.get();
            userJobDetailsRepository.deleteById(jobDetails.getEntityId());
            return "Success";
        }else{
            return "invalid EntityId";
        }
    }

    public String editJobByEntityId(String EntityId){
        Optional<UserJobDetails> optionalEntry = userJobDetailsRepository.findById(EntityId);
        if(optionalEntry.isPresent()){
            UserJobDetails jobDetails = optionalEntry.get();
            List<TimeLine> timelineList = jobDetails.getJobTimelineList();
            timelineList.add(new TimeLine(timelineList.size(),Instant.now(),"updateJob", "Job Updated ", "You Updated a Job "));
            //TODO:implement Edit job Details
            userJobDetailsRepository.save(jobDetails);
            return "Success";
        }else{
            return "invalid EntityId";
        }
    }
    // Method to create a new note
    public UserJobDetails createNewNote(String entityId, Note note) {
        return userJobDetailsRepository.findById(entityId).map(jobDetails -> {
            List<Note> notesList = jobDetails.getNotesList();
            int noteCounter = jobDetails.getNotesCounter();
            notesList.add(new Note(noteCounter, note.getNoteText(), note.getNoteCreatedBy(), Instant.now()));
            jobDetails.setNotesCounter(noteCounter + 1);
            jobDetails.setNotesList(notesList);
            jobDetails.getJobTimelineList().add(new TimeLine(
                    jobDetails.getJobTimelineList().size(), Instant.now(), "newNote",
                    "New Note Created", note.getNoteCreatedBy().concat(" added a new Note")));
            return userJobDetailsRepository.save(jobDetails);
        }).orElse(null);
    }

    // Method to edit an existing note
    public UserJobDetails editNote(String entityId, Note note) {
        return userJobDetailsRepository.findById(entityId).map(jobDetails -> {
            List<Note> notesList = jobDetails.getNotesList();
            boolean found = false;
            for (int i = 0; i < notesList.size(); i++) {
                if (notesList.get(i).getNoteIndex() == note.getNoteIndex()) {
                    notesList.set(i, note);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NoSuchElementException("Note not found");
            }
            return userJobDetailsRepository.save(jobDetails);
        }).orElse(null);
    }

    // Method to delete a note
    public UserJobDetails deleteNote(String entityId, Note note) {
        return userJobDetailsRepository.findById(entityId).map(jobDetails -> {
            List<Note> notesList = jobDetails.getNotesList();
            boolean found = false;
            for (int i = 0; i < notesList.size(); i++) {
                if (notesList.get(i).getNoteIndex() == note.getNoteIndex()) {
                    notesList.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NoSuchElementException("Note not found");
            }
            jobDetails.setNotesList(notesList);
            jobDetails.getJobTimelineList().add(new TimeLine(
                    jobDetails.getJobTimelineList().size(), Instant.now(), "deleteNote",
                    "Note Deleted", "You removed a Note"));
            return userJobDetailsRepository.save(jobDetails);
        }).orElse(null);
    }

    // Method to create a new task
    public UserJobDetails createNewTask(String entityId, Task task) {
        return userJobDetailsRepository.findById(entityId).map(jobDetails -> {
            int taskCounter = jobDetails.getTaskCounter();
            List<Task> taskList = jobDetails.getTasksList();
            taskList.add(new Task(taskCounter, jobDetails.getJobTimelineList().size(), task.getTaskName(), task.getTaskStatus()));
            jobDetails.setTaskCounter(taskCounter + 1);
            jobDetails.setTasksList(taskList);
            jobDetails.getJobTimelineList().add(new TimeLine(
                    jobDetails.getJobTimelineList().size(), Instant.now(), "task",
                    task.getTaskName(), task.getTaskStatus()));
            return userJobDetailsRepository.save(jobDetails);
        }).orElse(null);
    }

    // Method to edit an existing task
    public UserJobDetails editTask(String entityId, Task task) {
        return userJobDetailsRepository.findById(entityId).map(jobDetails -> {
            List<TimeLine> timeLineList = jobDetails.getJobTimelineList();
            TimeLine timeLine = timeLineList.get(task.getTimelineIndex());
            timeLine.setModificationDescription(task.getTaskStatus());
            timeLineList.set(timeLine.getTimelineIndex(), timeLine);
            jobDetails.setJobTimelineList(timeLineList);

            List<Task> taskList = jobDetails.getTasksList();
            boolean found = false;
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskIndex() == task.getTaskIndex()) {
                    taskList.set(i, task);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NoSuchElementException("Task not found");
            }
            jobDetails.setTasksList(taskList);
            return userJobDetailsRepository.save(jobDetails);
        }).orElse(null);
    }

    // Method to delete a task
    public String deleteTask(String entityId, Task task) {
        return userJobDetailsRepository.findById(entityId).map(jobDetails -> {
            List<Task> taskList = jobDetails.getTasksList();
            boolean found = false;
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskIndex() == task.getTaskIndex()) {
                    taskList.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NoSuchElementException("Task not found");
            }
            jobDetails.setTasksList(taskList);
            jobDetails.getJobTimelineList().add(new TimeLine(
                    jobDetails.getJobTimelineList().size(), Instant.now(), "task",
                    "Task Deleted", "You removed a Task"));
            userJobDetailsRepository.save(jobDetails);
            return "Success";
        }).orElse("invalid EntityId");
    }

    public UserJobDetails linkDocument(String entityId, LinkedDocuments document,String userName) {
        UserJobDetails jobDetails = userJobDetailsRepository.findById(entityId)
                .orElseThrow(() -> new NoSuchElementException("UserJobDetails not found with entityId: " + entityId));
        Documents uploadedDocument = documentRepository.findById(document.getDocumentId())
                .orElseThrow(() -> new NoSuchElementException("Document not found with ID: " + document.getDocumentId()));
        uploadedDocument.getJobsLinkedList().add(entityId);
        jobDetails.getLinkedDocumentsList().add(document);
        jobDetails.getJobTimelineList().add(new TimeLine(
                jobDetails.getJobTimelineList().size(), Instant.now(), "documentLink",
                "Document Linked", userName.concat(" Linked a Document to this Job")));
        documentRepository.save(uploadedDocument);
        return userJobDetailsRepository.save(jobDetails);
    }


    public UserJobDetails unLinkDocument(String entityId, String documentId,String userName) {
        UserJobDetails jobDetails = userJobDetailsRepository.findById(entityId)
                .orElseThrow(() -> new NoSuchElementException("UserJobDetails not found with entityId: " + entityId));
        Documents uploadedDocument = documentRepository.findById(documentId)
                .orElseThrow(() -> new NoSuchElementException("Document not found with ID: " + documentId));
        uploadedDocument.getJobsLinkedList().remove(entityId);
        jobDetails.getLinkedDocumentsList().removeIf(doc -> doc.getDocumentId().equals(documentId));
        jobDetails.getJobTimelineList().add(new TimeLine(
                jobDetails.getJobTimelineList().size(), Instant.now(), "documentUnLink",
                "Document unlinked", userName.concat(" unlinked a Document to this Job")));
        documentRepository.save(uploadedDocument);
        return userJobDetailsRepository.save(jobDetails);
    }


}
