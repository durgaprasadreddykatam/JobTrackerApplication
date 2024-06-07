package com.jobTracker.JobTrackerApplication.Configurations;

import com.jobTracker.JobTrackerApplication.Entities.UserAccount;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
@Component
public class JwtResource {
    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        var keyPairGenerator = java.security.KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();

    }
    @Bean
    public RSAKey rsaKey(KeyPair keyPair) throws ParseException {
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(String.valueOf(UUID.randomUUID()))
                .build();

    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);
//        var jwkSelector = new JWKSource() {
//            @Override
//            public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
//                return jwkSelector.select(jwkSet);
//            }
//
//        };
        return  (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

    @Bean
    public JwtEncoder jwt(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);

    }


}
