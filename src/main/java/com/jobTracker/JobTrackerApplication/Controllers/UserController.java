package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import com.jobTracker.JobTrackerApplication.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/test")
    public String testApi(){
        return "API Initiated without Security";
    }

    @PostMapping("/createAccount")
    public String createUserAccount(@RequestBody Map<String,Object> userMap){
        UserAccount user =new UserAccount();
        user.setEmail((String) userMap.get("email"));
        user.setFirstName((String) userMap.get("firstName"));
        user.setLastName((String) userMap.get("lastName"));
        user.setPassword((String) userMap.get("password"));
        user.setSignUpPlatform((String) userMap.get("signUpPlatform"));
        user.setUserRoles(Collections.singletonList("appUser"));
        UserAccount userDetails =userAccountService.createUserAccount(user);
        return ("user with id has been Succesfully created"+ userDetails.getUserId());
    }

    @GetMapping("/signIn")
    public String signInTestApi(){
        return "API Initiated with Security";
    }
}
