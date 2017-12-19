package com.vdenotaris.spring.boot.security.saml.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout (HttpServletResponse response){
        return "logout";
    }
}
