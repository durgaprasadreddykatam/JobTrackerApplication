package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.CompanyEVerifyStatus;
import com.jobTracker.JobTrackerApplication.Repositories.CompanyEVerifyStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyEVerifyService {
    @Autowired
    private CompanyEVerifyStatusRepository  companyEVerifyStatusRepository;

    public String addListToDb(List<CompanyEVerifyStatus> companiesList) {
        try{
            companyEVerifyStatusRepository.saveAll(companiesList);
            return "Success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
