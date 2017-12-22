package com.zemoso.zesharing.security.saml.web.config.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sudheerds
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    //public static final String HEADER_SECURITY_TOKEN = "x-auth-token";
    public static final String HEADER_SECURITY_TOKEN = "jwt";

    public JwtAuthenticationFilter(final String matcher, AuthenticationManager authenticationManager) {
        super(matcher);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String token = JWTUtil.getJwtTokenFromCookies(request);
        //no need to check if you need to validate token here and JWTAuthProvide authenticate does it
        if(token!=null){
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
            return getAuthenticationManager().authenticate(jwtAuthenticationToken);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }


}
