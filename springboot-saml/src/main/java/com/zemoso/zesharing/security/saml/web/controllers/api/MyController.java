package com.zemoso.zesharing.security.saml.web.controllers.api;

import com.nimbusds.jwt.SignedJWT;
import com.zemoso.zesharing.security.saml.web.config.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author sudheerds
 */
@RestController
@RequestMapping("/api")
public class MyController {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @GetMapping(value = "/getV")
    public String getValue(HttpServletRequest request, @CookieValue("jwt") String jwtToken) {
        String email = null;
        if (JWTUtil.isJWTValid(jwtToken)) {
            try {
                SignedJWT signedJWT = SignedJWT.parse(jwtToken);
                net.minidev.json.JSONObject claims = (net.minidev.json.JSONObject) signedJWT.getJWTClaimsSet().getClaims().get("user");
                claims.getAsString("emailid");
                email = claims.getAsString("emailId");
                return email+ " has logged in";
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        return "The bat is in the cave !";
    }
}
