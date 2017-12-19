package com.vdenotaris.spring.boot.security.saml.web.stereotypes;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class CustomAuthUser {
    private String emailId;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomAuthUser(String emailId, String username, Collection<? extends GrantedAuthority> authorities) {
        this.emailId = emailId;
        this.username = username;
        this.authorities = authorities;
    }

    public CustomAuthUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }
}
