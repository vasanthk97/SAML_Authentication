package com.zemoso.zesharing.security.saml.web.config.jwt;

import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import java.text.ParseException;

/**
 * @author sudheerds
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

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
                logger.error(e.getMessage());
            }
            //TODO pass a User object rather than signedJWT, user from db
            return new JwtAuthenticationToken(signedJWT, null, null);
        }
        return null;
    }
}