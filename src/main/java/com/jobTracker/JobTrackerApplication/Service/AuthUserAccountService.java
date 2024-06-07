package com.jobTracker.JobTrackerApplication.Service;

import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import com.jobTracker.JobTrackerApplication.Repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserAccountService implements UserDetailsService {
    @Autowired
    private  UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> user =userAccountRepository.getUserAccountByEmail(username.toLowerCase());
        if(!user.isPresent()) throw new UsernameNotFoundException(username);
        else {
            UserAccount fetchedUser = user.get();
            return User.builder()
                    .username(fetchedUser.getEmail())
                    .password(fetchedUser.getPassword())
                    .disabled(!fetchedUser.isActive())
                    .roles(fetchedUser.getUserRoles())
                    .build();
        }
    }
}
