package com.vdenotaris.spring.boot.security.saml.web.config.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.vdenotaris.spring.boot.security.saml.web.config.SecurityConstant;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author sudheerds
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        Assert.notNull(authentication, "Authentication is missing");

        Assert.isInstanceOf(JwtAuthenticationToken.class, authentication,
                "This method only accepts JwtAuthenticationToken");

        String jwtToken = authentication.getName();

        if (authentication.getPrincipal() == null || jwtToken == null) {
            throw new AuthenticationCredentialsNotFoundException("Authentication token is missing");
        }


        if(JWTUtil.isJWTValid(jwtToken)){
            SignedJWT signedJWT = null;
            try {
                signedJWT = SignedJWT.parse(jwtToken);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new JwtAuthenticationToken(signedJWT, null, null);
        }
        return null;
    }
}