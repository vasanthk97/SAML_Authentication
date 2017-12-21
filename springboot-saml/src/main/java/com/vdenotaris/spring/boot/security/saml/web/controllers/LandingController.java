/*
 * Copyright 2017 Vincenzo De Notaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CustomAuthUser;
import org.owasp.esapi.waf.internal.InterceptingHTTPServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CurrentUser;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LandingController.class);
    @Autowired CustomAuthUser customAuthUser;

	@RequestMapping("/landing")
	public String landing(Model model) {
		logger.info("logged in user: "+customAuthUser.getUsername());
		model.addAttribute("username",customAuthUser.getUsername());
		return "landing";
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		logger.info("logout is requested");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8082/saml/logout",Boolean.class, HttpServletResponse.class);
        //handleLogOutResponse(request,response);
		return "ok";
	}




}
