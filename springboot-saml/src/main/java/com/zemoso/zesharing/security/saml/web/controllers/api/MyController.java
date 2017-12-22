package com.zemoso.zesharing.security.saml.web.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sudheerds
 */
@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping(value = "/getV")
    public String getValue() {
        return "The bat is in the cave !";
    }
}
