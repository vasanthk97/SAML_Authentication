package com.zemoso.zesharing.security.saml.web.config.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.zemoso.zesharing.security.saml.web.config.SecurityConstant;
import com.zemoso.zesharing.security.saml.web.stereotypes.CustomAuthUser;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.zemoso.zesharing.security.saml.web.config.jwt.JwtAuthenticationFilter.HEADER_SECURITY_TOKEN;

/**
 * Created by sudheerds on 21/12/17.
 */
public class JWTUtil {
    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private JWTUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getJwtTokenFromCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equalsIgnoreCase(HEADER_SECURITY_TOKEN)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static boolean isJWTValid(String jwtToken){
        final SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(jwtToken);

            boolean isVerified = signedJWT.verify(new MACVerifier(SecurityConstant.JWT_SECRET.getBytes()));

            if(!isVerified){
                throw new BadCredentialsException("Invalid token signature");
            }

            //is token expired ?
            LocalDateTime expirationTime = LocalDateTime.ofInstant(
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(), ZoneId.systemDefault());

            if (LocalDateTime.now(ZoneId.systemDefault()).isAfter(expirationTime)) {
                throw new CredentialsExpiredException("Token expired");
            }

        } catch (ParseException e) {
            throw new InternalAuthenticationServiceException("Unreadable token");
        } catch (JOSEException e) {
            throw new InternalAuthenticationServiceException("Unreadable signature");
        }
        return true;
    }

    public static SignedJWT buildJWTToken(CustomAuthUser user) {
        final DateTime dateTime = DateTime.now();

        //build claims
        JWTClaimsSet.Builder jwtClaimsSetBuilder = new JWTClaimsSet.Builder();
        jwtClaimsSetBuilder.expirationTime(dateTime.plusMinutes(120).toDate());
        jwtClaimsSetBuilder.claim("zemoso", "labs");
        jwtClaimsSetBuilder.claim("user",user);

        //signature
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), jwtClaimsSetBuilder.build());
        try {
            signedJWT.sign(new MACSigner(SecurityConstant.JWT_SECRET));
        } catch (JOSEException e) {
           logger.error(e.getMessage());
        }
        return signedJWT;
    }
}
