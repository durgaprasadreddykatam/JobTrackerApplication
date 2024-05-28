package com.jobTracker.JobTrackerApplication.Repositories;

import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount,String> {

    Optional<UserAccount> getUserAccountByEmail(String email);
    long countUserAccountByEmail(String email);
}
