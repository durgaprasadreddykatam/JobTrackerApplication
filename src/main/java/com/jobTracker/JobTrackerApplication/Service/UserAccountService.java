package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import com.jobTracker.JobTrackerApplication.Exceptions.DuplicateRecordException;
import com.jobTracker.JobTrackerApplication.Repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public UserAccount createUserAccount(UserAccount userAccount){
        userAccount.setEmail(userAccount.getEmail().toLowerCase());
        long count = userAccountRepository.countUserAccountByEmail(userAccount.getEmail());
        if (count>0) throw new DuplicateRecordException("email id already registered");
        String encryptedPassword = passwordEncoder.encode(userAccount.getPassword());
        userAccount.setPassword(encryptedPassword);
        userAccount.setActive(true);
        return userAccountRepository.save(userAccount);

    }
}
