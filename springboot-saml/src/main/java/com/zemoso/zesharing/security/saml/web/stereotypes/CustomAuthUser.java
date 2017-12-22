package com.zemoso.zesharing.security.saml.web.stereotypes;

import org.springframework.stereotype.Component;

@Component
public class CustomAuthUser {
    private String emailId;
    private String username;

    public CustomAuthUser(String emailId, String username) {
        this.emailId = emailId;
        this.username = username;
    }

    public CustomAuthUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }
}
