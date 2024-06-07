package com.jobTracker.JobTrackerApplication.Controllers;

import com.jobTracker.JobTrackerApplication.Configurations.JwtTokenGenerator;
import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import com.jobTracker.JobTrackerApplication.Service.UserAccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*",allowCredentials = "true")
public class UserController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @GetMapping("/test")
    public String testApi(){
        return "API Initiated without Security";
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Map<String,String>> createUserAccount(@RequestBody Map<String,Object> userMap,HttpServletResponse cookieResp){
        UserAccount user =new UserAccount();
        Map<String, String> response = new HashMap<>();
        user.setEmail((String) userMap.get("email"));
        user.setFirstName((String) userMap.get("firstName"));
        user.setLastName((String) userMap.get("lastName"));
        user.setPassword((String) userMap.get("password"));
        user.setSignUpPlatform((String) userMap.get("signUpPlatform"));
        user.setEmailVerified(false);
        user.setDefaultResumeUploaded(false);
        user.setUserRoles("appUser");
        UserAccount userDetails =userAccountService.createUserAccount(user);
        Cookie cookie = setCookie(jwtTokenGenerator.generateJwtForSignUp(userDetails));
        cookieResp.addCookie(cookie);
        response.put("message", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<Map<String,String>> signIn(HttpServletRequest request,HttpServletResponse cookieResp){
        String authHeader = request.getHeader("Authorization");
        Map<String, String> response = new HashMap<>();
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            response.put("message", "tokenAlreadyPresent");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if(authHeader != null && authHeader.startsWith("Basic")) {
            Cookie cookie = setCookie(jwtTokenGenerator.generateJwt(SecurityContextHolder.getContext().getAuthentication()));
            cookieResp.addCookie(cookie);
            response.put("message","success" );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "Invalid Credentials");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String,String>> getUser() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully Authenticated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Cookie setCookie(String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }


}
