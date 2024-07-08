package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.JobDetails.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CompanyRepository extends MongoRepository<Company,String> {
    Integer countAllByWebsiteAndAddedFromExtensionTrue(String companyWebsite);
    List<Company>  getAllByWebsiteAndAddedFromExtensionTrue(String companyWebsite);

    Integer countCompanyByWebsite(String website);

    Company findByCompanyId(String companyId);
}
