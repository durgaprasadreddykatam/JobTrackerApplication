package com.jobTracker.JobTrackerApplication.Configurations;

import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import com.jobTracker.JobTrackerApplication.Repositories.UserAccountRepository;
import com.jobTracker.JobTrackerApplication.Service.AuthUserAccountService;
import jakarta.ws.rs.core.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenGenerator {
    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    AuthUserAccountService authUserAccountService;

    public String generateJwt(Authentication authentication){
        UserAccount userAccount = userAccountRepository.getUserAccountByEmail(authentication.getName()).get();
        return generateToken(userAccount, authentication);
    }

    public String generateJwtForSignUp(UserAccount userAccount){
        UserDetails authentication = authUserAccountService.loadUserByUsername(userAccount.getEmail());
        return generateToken(userAccount, authentication);
    }

    private String generateToken(UserAccount userAccount, Authentication authentication) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 60 * 24))
                .subject(authentication.getName())
                .claim("userId", userAccount.getUserId())
                .claim("firstName", userAccount.getFirstName())
                .claim("lastName", userAccount.getLastName())
                .claim("scope", authorities)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String generateToken(UserAccount userAccount, UserDetails authentication) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 60 * 24))
                .subject(authentication.getUsername())
                .claim("userId", userAccount.getUserId())
                .claim("firstName", userAccount.getFirstName())
                .claim("lastName", userAccount.getLastName())
                .claim("scope", authorities)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
    public String getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return (String) jwt.getClaim("userId");

    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return (String) jwt.getClaim("firstName");

    }


}
