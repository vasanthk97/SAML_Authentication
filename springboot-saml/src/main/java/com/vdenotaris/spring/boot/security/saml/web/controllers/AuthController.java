package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.nimbusds.jwt.SignedJWT;
import com.vdenotaris.spring.boot.security.saml.web.config.jwt.JWTUtil;
import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CustomAuthUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sudheerds on 21/12/17.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {



    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,@CookieValue(value = "jwt", defaultValue = "notPresent") String accessToken) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return new ModelAndView("index");
        } else {
            if(accessToken.equalsIgnoreCase("notPresent")){
                SAMLCredential samlCredential = (SAMLCredential) authentication.getCredentials();
                CustomAuthUser user = new CustomAuthUser();
                user.setEmailId(samlCredential.getNameID().getValue());
                user.setUsername("");
                user.setAuthorities(null);
                authentication = new UsernamePasswordAuthenticationToken(user,null,null);
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                // generate a jwt token
                SignedJWT signedJWT = JWTUtil.buildJWTToken(user);

            /* set cookie on browser, ideally it should not be a cookie set but should go in as a request header ,
            using filter */
                Cookie cookie = new Cookie("jwt",signedJWT.serialize());
                cookie.setPath("/");
                cookie.setDomain("ssocircle.com");
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }else{
                if(JWTUtil.isJWTValid(accessToken)){
                    return new ModelAndView("landing");
                }

            }
        }
        return new ModelAndView("index");
    }
}
