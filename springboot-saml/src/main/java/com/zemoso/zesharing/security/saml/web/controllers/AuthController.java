package com.zemoso.zesharing.security.saml.web.controllers;

import com.nimbusds.jwt.SignedJWT;
import com.zemoso.zesharing.security.saml.web.config.jwt.JWTUtil;
import com.zemoso.zesharing.security.saml.web.stereotypes.CustomAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sudheerds on 21/12/17.
 * Updated by spolley on 22/12/17.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    SAMLCredential samlCredential;

    /**
     * If the user is not authenticated from IDP this controller will send the user back to
     * Login page. If user is authenticated from IDP but JWT token is not present it will create
     * a token. If user authenticated and JWT token is present then it will redirect the user
     * to an index page.
     *
     * @param request
     * @param response
     * @param accessToken
     * @return
     */

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @CookieValue(value = "jwt", defaultValue = "notPresent") String accessToken) {

        //Getting zesharing auth from context.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            //if not authenticated by idp.
            return "redirect:/index";
        } else {
            if (accessToken.equalsIgnoreCase("notPresent")) {

                //if JWT token is not present.

                SAMLCredential userSamlCredential = null;

                try {
                    //throws ClassCastException
                    userSamlCredential = (SAMLCredential) authentication.getCredentials();

                } catch (ClassCastException e) {
                    logger.error(e.getMessage());
                }

                //Due to bug in SAML, Credential gets losts from security context.
                //So keep a copy.
                if (userSamlCredential != null) {
                    this.samlCredential = userSamlCredential;

                } else {
                    userSamlCredential = this.samlCredential;

                }

                CustomAuthUser user = new CustomAuthUser();
                user.setEmailId(userSamlCredential.getNameID().getValue());
                user.setUsername("");

                authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                // generate a jwt token
                SignedJWT signedJWT = JWTUtil.buildJWTToken(user);

                //Set cookie
                Cookie cookie = new Cookie("jwt", signedJWT.serialize());
                cookie.setPath("/");

                response.addCookie(cookie);
                return "redirect:/auth/redirection";

            } else {
                if (JWTUtil.isJWTValid(accessToken)) {
                    //if JWT token is present.
                    return "redirect:/auth/redirection";
                }

            }
        }
        return "redirect:/index";
    }

    /**
     * This controller will redirect user to Ember login/protected page.
     * @param model
     * @return
     */
    @RequestMapping("/redirection")
    public String redirection(Model model) {
        return "redirection";
    }
}
